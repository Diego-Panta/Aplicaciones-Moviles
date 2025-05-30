package com.example.panta_piscoche_login_register

import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etName = findViewById(R.id.etNameSignup)
        etEmail = findViewById(R.id.etEmailSignup)
        etPassword = findViewById(R.id.etPasswordSignup)
        btnRegister = findViewById(R.id.btnSignupUser)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar en SharedPreferences
            val sharedPref = getSharedPreferences("userData", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("name", name)
                putString("email", email)
                putString("password", password)
                apply()
            }

            Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnRegresar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}