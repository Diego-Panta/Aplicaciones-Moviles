package com.example.clinicarobles_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val doctores = arrayListOf(
        Doctor("johanLopez@clinicarobles.com", "1234", "Dr. Johan Lopez"),
        Doctor("diegoPanta@clinicarobles.com", "5678", "Dr. Diego Panta")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val email = findViewById<EditText>(R.id.editEmail)
        val password = findViewById<EditText>(R.id.editPassword)

        btnLogin.setOnClickListener {
            val correo = email.text.toString()
            val pass = password.text.toString()

            val doctor = doctores.find { it.correo == correo && it.contrasena == pass }

            if (doctor != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("nombreDoctor", doctor.nombre)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}