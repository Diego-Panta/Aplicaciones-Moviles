package com.example.quiz_uns_panta

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var questionManager: QuestionManager
    private lateinit var opciones: List<TextView>
    private lateinit var txtPregunta: TextView
    private lateinit var txtPuntaje: TextView
    private lateinit var txtTemporizador: TextView
    private lateinit var progressPreguntas: ProgressBar
    private lateinit var btnSiguiente: Button

    private var contadorTiempo: CountDownTimer? = null
    private val TIEMPO_TOTAL = 30 * 1000L // 30 segundos
    private var opcionSeleccionada = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionManager = QuestionManager(this)

        txtPregunta = findViewById(R.id.txtPregunta)
        txtPuntaje = findViewById(R.id.txtPuntaje)
        txtTemporizador = findViewById(R.id.txtTemporizador)
        btnSiguiente = findViewById(R.id.btnSiguiente)

        btnSiguiente.isEnabled = false

        progressPreguntas = findViewById(R.id.progressPreguntas)
        progressPreguntas.max = 15
        progressPreguntas.progress = 0

        btnSiguiente.setOnClickListener {
            mostrarSiguientePregunta()
        }
        opciones = listOf(
            findViewById(R.id.btnOpcion1),
            findViewById(R.id.btnOpcion2),
            findViewById(R.id.btnOpcion3),
            findViewById(R.id.btnOpcion4)
        )
        mostrarSiguientePregunta()
    }

    private fun iniciarTemporizador() {
        contadorTiempo?.cancel()
        contadorTiempo = object : CountDownTimer(TIEMPO_TOTAL, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val segundosRestantes = (millisUntilFinished / 1000).toInt()
                txtTemporizador.text = segundosRestantes.toString()
            }

            override fun onFinish() {
                txtTemporizador.text = "0"
                deshabilitarOpciones()

                // Resaltar la respuesta correcta aunque no se haya respondido
                resaltarRespuestaCorrecta()

                // Avanzar automáticamente después de 1 segundo
                Handler(Looper.getMainLooper()).postDelayed({
                    mostrarSiguientePregunta()
                }, 1000)
            }
        }.start()
    }

    private fun mostrarSiguientePregunta() {
        opcionSeleccionada = false
        btnSiguiente.isEnabled = false
        habilitarOpciones()

        val pregunta = questionManager.obtenerPreguntaActual()
        val nombreJugador = intent.getStringExtra("nombre_jugador") ?: "Jugador"

        progressPreguntas.progress = questionManager.indiceActual + 1

        if (pregunta != null) {
            txtPregunta.text = pregunta.pregunta
            opciones.forEachIndexed { i, boton ->
                boton.background = ContextCompat.getDrawable(this, R.drawable.boton_opcion)
                boton.setTextColor(Color.parseColor("#1A1A1A"))
                boton.text = pregunta.opciones[i]
                boton.setOnClickListener {
                    if (!opcionSeleccionada) {
                        opcionSeleccionada = true
                        contadorTiempo?.cancel()

                        val esCorrecta = questionManager.verificarRespuesta(boton.text.toString())

                        if (esCorrecta) {
                            boton.background =
                                ContextCompat.getDrawable(this, R.drawable.boton_correcto)
                            boton.setTextColor(Color.parseColor("#2A4D4F")) // Verde oscuro
                            mostrarAnimacion("+${questionManager.indiceActual}", boton)
                        } else {
                            boton.background =
                                ContextCompat.getDrawable(this, R.drawable.boton_incorrecto)
                            boton.setTextColor(Color.parseColor("#8B0000")) // Rojo oscuro
                            mostrarAnimacion("-1", boton)
                            resaltarRespuestaCorrecta()
                        }

                        txtPuntaje.text = "${questionManager.puntaje}"
                        deshabilitarOpciones()
                        btnSiguiente.isEnabled = true

                        //Avanza al final cuandotodo está resuelto visualmente
                        questionManager.avanzarPregunta()
                    }
                }
            }

            iniciarTemporizador()
        } else {
            val intent = Intent(this, ScoreResultsActivity::class.java)
            intent.putExtra("nombre_jugador", nombreJugador)
            intent.putExtra("puntaje_jugador", questionManager.puntaje)
            startActivity(intent)
            finish()
        }
    }

    private fun mostrarAnimacion(texto: String, view: View) {
        val anim = TextView(this)
        anim.text = texto
        anim.setTextColor(if (texto.startsWith("+")) Color.GREEN else Color.RED)
        anim.textSize = 35f
        anim.setTypeface(ResourcesCompat.getFont(this, R.font.bungee_regular), Typeface.BOLD)
        anim.translationY = 0f

        val layout = findViewById<ConstraintLayout>(R.id.quizLayout)
        layout.addView(anim)

        val coordenadas = IntArray(2)
        view.getLocationOnScreen(coordenadas)
        anim.x = coordenadas[0].toFloat()
        anim.y = coordenadas[1].toFloat()

        anim.animate()
            .translationYBy(-100f)
            .alpha(0f)
            .setDuration(1000)
            .withEndAction { layout.removeView(anim) }
            .start()
    }

    private fun deshabilitarOpciones() {
        opciones.forEach { it.isEnabled = false }
    }

    private fun habilitarOpciones() {
        opciones.forEach { it.isEnabled = true }
    }

    private fun resaltarRespuestaCorrecta() {
        val respuestaCorrecta = questionManager.obtenerPreguntaActual()?.respuestaCorrecta
        opciones.forEach {
            if (it.text == respuestaCorrecta) {
                it.background = ContextCompat.getDrawable(this, R.drawable.boton_correcto)
                it.setTextColor(Color.parseColor("#2A4D4F")) // Mismo verde oscuro que usas
            }
        }
    }

}