package com.example.panta_piscoche_form_register

import Student
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var etApellidos: EditText
    private lateinit var etNombres: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnActualizar: Button
    private lateinit var tvCodigo: TextView
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        dbHelper = DBHelper(this)

        // Obtener las referencias de los elementos UI
        etApellidos = findViewById(R.id.etApellidos)
        etNombres = findViewById(R.id.etNombres)
        etCorreo = findViewById(R.id.etCorreo)
        btnActualizar = findViewById(R.id.btnActualizar)
        tvCodigo = findViewById(R.id.tvCodigo)

        // Recuperar el objeto Student enviado desde la actividad anterior
        student = intent.getParcelableExtra("student")
        student?.let {
            // Mostrar los datos actuales del estudiante en los campos de texto
            tvCodigo.text = "Código: ${it.codigo}"
            etApellidos.setText(it.apellidos)
            etNombres.setText(it.nombres)
            etCorreo.setText(it.correo)
        }

        // Configurar el botón de actualizar
        btnActualizar.setOnClickListener {
            val nuevoApellidos = etApellidos.text.toString()
            val nuevosNombres = etNombres.text.toString()
            val nuevoCorreo = etCorreo.text.toString()

            if (nuevoApellidos.isNotEmpty() && nuevosNombres.isNotEmpty() && nuevoCorreo.isNotEmpty()) {
                student?.let {
                    dbHelper.updateStudent(it.codigo, nuevoApellidos, nuevosNombres, nuevoCorreo)
                    Toast.makeText(this, "Estudiante actualizado", Toast.LENGTH_SHORT).show()

                    // Devolver el estudiante actualizado a la actividad principal
                    val updatedStudent = it.copy(apellidos = nuevoApellidos, nombres = nuevosNombres, correo = nuevoCorreo)
                    val resultIntent = Intent()
                    resultIntent.putExtra("updatedStudent", updatedStudent)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish() // Cerrar la actividad después de guardar los cambios
                }
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}