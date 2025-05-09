package com.example.panta_piscoche_form_register

import Student
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewStudentActivity : AppCompatActivity(), StudentAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var dbHelper: DBHelper
    private lateinit var spinnerOrden: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_student)

        dbHelper = DBHelper(this)
        recyclerView = findViewById(R.id.recyclerViewEstudiantes)
        spinnerOrden = findViewById(R.id.spinnerOrden)

        val opciones = arrayOf("Ordenar por código", "Ordenar por apellidos")
        spinnerOrden.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)

        spinnerOrden.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val orden = if (position == 0) "codigo ASC" else "apellidos ASC"
                mostrarEstudiantes(orden)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun mostrarEstudiantes(orden: String) {
        val lista = dbHelper.getAllStudents(orden)
        adapter = StudentAdapter(lista, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onEditClick(student: Student) {
        val intent = Intent(this, EditStudentActivity::class.java)
        intent.putExtra("student", student)
        startActivityForResult(intent, 1) // 1 es un código de solicitud arbitrario
    }

    override fun onDeleteClick(student: Student) {
        // Eliminar el estudiante de la base de datos
        dbHelper.deleteStudent(student.codigo)
        mostrarEstudiantes("codigo ASC") // Recargar la lista
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val updatedStudent = data?.getParcelableExtra<Student>("updatedStudent")
            updatedStudent?.let {
                // Actualizar la lista de estudiantes para reflejar los cambios
                mostrarEstudiantes("codigo ASC")
            }
        }
    }
}