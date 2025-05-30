package com.example.panta_piscoche_form_register

import Student
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "EstudiantesDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE estudiantes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                codigo TEXT,
                apellidos TEXT,
                nombres TEXT,
                correo TEXT
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS estudiantes")
        onCreate(db)
    }

    fun insertStudent(codigo: String, apellidos: String, nombres: String, correo: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("codigo", codigo)
            put("apellidos", apellidos)
            put("nombres", nombres)
            put("correo", correo)
        }
        db.insert("estudiantes", null, values)
    }

    fun getAllStudents(orderBy: String): List<Student> {
        val students = mutableListOf<Student>()
        val db = readableDatabase

        // Si se ordena por apellidos, aplicar COLLATE NOCASE para que no distinga entre mayúsculas y minúsculas
        val fixedOrder = if (orderBy.contains("apellidos", ignoreCase = true)) {
            "apellidos COLLATE NOCASE ASC"
        } else {
            orderBy // Si no, mantener el orden que venga
        }

        // Ejecutar la consulta con el orden modificado
        val cursor = db.query("estudiantes", null, null, null, null, null, fixedOrder)

        if (cursor.moveToFirst()) {
            do {
                val student = Student(
                    cursor.getString(cursor.getColumnIndexOrThrow("codigo")),
                    cursor.getString(cursor.getColumnIndexOrThrow("apellidos")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nombres")),
                    cursor.getString(cursor.getColumnIndexOrThrow("correo"))
                )
                students.add(student)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return students
    }

    fun deleteStudent(codigo: String) {
        val db = writableDatabase
        db.delete("estudiantes", "codigo = ?", arrayOf(codigo))
    }

    fun updateStudent(codigo: String, apellidos: String, nombres: String, correo: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("apellidos", apellidos)
            put("nombres", nombres)
            put("correo", correo)
        }
        db.update("estudiantes", values, "codigo = ?", arrayOf(codigo))
    }
}