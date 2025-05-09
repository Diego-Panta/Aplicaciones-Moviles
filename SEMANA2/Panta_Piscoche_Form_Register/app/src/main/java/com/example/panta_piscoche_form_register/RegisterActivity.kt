package com.example.panta_piscoche_form_register

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var etCodigo: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etNombres: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnGuardar: Button

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etCodigo = findViewById(R.id.etCodigo)
        etApellidos = findViewById(R.id.etApellidos)
        etNombres = findViewById(R.id.etNombres)
        etCorreo = findViewById(R.id.etCorreo)
        btnGuardar = findViewById(R.id.btnRegistrar)

        dbHelper = DBHelper(this)

        btnGuardar.setOnClickListener {
            val codigo = etCodigo.text.toString()
            val apellidos = etApellidos.text.toString()
            val nombres = etNombres.text.toString()
            val correo = etCorreo.text.toString()

            // Validación de campos vacíos
            if (codigo.isEmpty() || apellidos.isEmpty() || nombres.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación del código (debe ser exactamente 10 caracteres)
            if (codigo.length != 10) {
                Toast.makeText(this, "El código debe tener 10 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación del correo (debe ser un formato de correo válido)
            if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Si todo está correcto, se guarda el estudiante
            dbHelper.insertStudent(codigo, apellidos, nombres, correo)
            Toast.makeText(this, "Estudiante registrado", Toast.LENGTH_SHORT).show()
            finish() // Regresa a la actividad anterior
        }
    }
}