package com.example.panta_piscoche_form_register

import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PanelActivity : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var btnViewStudents: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel)

        btnRegister = findViewById(R.id.btnRegisterUser)
        btnViewStudents = findViewById(R.id.btnViewStudents)
        btnLogout = findViewById(R.id.btnLogout)

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnViewStudents.setOnClickListener {
            startActivity(Intent(this, ViewStudentActivity::class.java))
        }

        btnLogout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}