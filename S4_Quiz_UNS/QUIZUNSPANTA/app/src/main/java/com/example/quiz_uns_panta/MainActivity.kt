package com.example.quiz_uns_panta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val nombreInput = findViewById<EditText>(R.id.editNombre)

        btnIniciar.setOnClickListener {
            val nombreJugador = nombreInput.text.toString().trim()

            if (nombreJugador.isEmpty()) {
                nombreInput.error = "Por favor, ingresa tu nombre"
                nombreInput.requestFocus()
            } else {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("nombre_jugador", nombreJugador)
                startActivity(intent)
            }
        }
    }
}