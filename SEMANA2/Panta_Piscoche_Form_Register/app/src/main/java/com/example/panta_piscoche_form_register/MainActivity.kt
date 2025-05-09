package com.example.panta_piscoche_form_register

import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    // Usuario estático (puedes agregar más si deseas)
    private val staticUsers = listOf(
        User("admin@uns.edu.pe", "admin123", "Admin"),
        User("docente@uns.edu.pe", "docente123", "Docente")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.etEmailLogin)
        etPassword = findViewById(R.id.etPasswordLogin)
        btnLogin = findViewById(R.id.btnLoginUser)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = staticUsers.find { it.email == email && it.password == password }

            if (user != null) {
                Toast.makeText(this, "¡Login exitoso como ${user.role}!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PanelActivity::class.java)
                intent.putExtra("userName", user.role)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Clase de datos para representar usuario
    data class User(val email: String, val password: String, val role: String)
}