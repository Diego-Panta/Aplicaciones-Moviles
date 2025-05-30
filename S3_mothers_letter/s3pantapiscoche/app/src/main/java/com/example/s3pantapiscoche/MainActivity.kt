package com.example.s3pantapiscoche


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.VibrationEffect
import android.os.Vibrator
import android.content.Context
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val btnMostrarMensaje = findViewById<Button>(R.id.btnMostrarMensaje)
        val textViewMensaje = findViewById<TextView>(R.id.textViewMensaje)

        // Referencias a los TextViews que cambiarán de color
        val tituloFeliz = findViewById<TextView>(R.id.tituloFeliz)
        val tituloMadre = findViewById<TextView>(R.id.tituloMadre)
        val textoFrase = findViewById<TextView>(R.id.textoFrase)

        btnMostrarMensaje.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            if (nombre.isNotEmpty()) {

                for (i in 0..20) {
                    mostrarCorazonAnimado()
                    vibrarTelefono()
                }
                cambiarColorTextos()
                val mensaje = "¡Feliz Día de la Madre, $nombre!"
                textViewMensaje.text = mensaje
            } else {
                textViewMensaje.text = "Por favor, ingresa un nombre."
            }
        }
    }

    private fun vibrarTelefono() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(200)
        }
    }

    private fun cambiarColorTextos() {
        // Cambiar colores al estilo hover
        val tituloFeliz = findViewById<TextView>(R.id.tituloFeliz)
        val tituloMadre = findViewById<TextView>(R.id.tituloMadre)
        val textoFrase = findViewById<TextView>(R.id.textoFrase)

        tituloFeliz.setTextColor(Color.parseColor("#FF4081")) // Rosa
        tituloMadre.setTextColor(Color.parseColor("#FF4081"))
        textoFrase.setTextColor(Color.parseColor("#FF4081"))
    }

    private fun mostrarCorazonAnimado() {
        val corazon = ImageView(this)
        corazon.setImageResource(R.drawable.corazon_2)

        val layout = findViewById<FrameLayout>(R.id.frameCorazones)

        val size = (80..120).random() // Tamaño aleatorio para variedad
        val params = FrameLayout.LayoutParams(size, size)
        params.leftMargin = (0..layout.width - size).random()
        params.topMargin = layout.height // Aparece justo abajo de la pantalla
        corazon.layoutParams = params

        layout.addView(corazon)

        corazon.translationY = layout.height.toFloat() // Posición inicial fuera de vista

        corazon.animate()
            .translationYBy(-layout.height.toFloat() - 300f) // Se mueve hacia arriba
            .alpha(0f)
            .setDuration(3000)
            .withEndAction { layout.removeView(corazon) }
            .start()
    }
}