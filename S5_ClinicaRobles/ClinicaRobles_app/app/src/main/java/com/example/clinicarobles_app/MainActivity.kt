package com.example.clinicarobles_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EspecialidadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nombreDoctor = intent.getStringExtra("nombreDoctor")
        val tvNameDoctor = findViewById<TextView>(R.id.tvNameDoctor)
        tvNameDoctor.text = "$nombreDoctor"

        val especialidades = listOf(
            Especialidad("Medicina Física", "Dr. Luis Vasquez", "8am - 2pm", R.drawable.ic_med_general),
            Especialidad("Medicina General", "Dr. Elwis Laveriano Hoyos\nDra. Maria Cristina Saavedra", "7am - 1pm", R.drawable.ic_med_general),
            Especialidad("Medicina Interna", "Dr. Luis Cabrera Cipiran\nDra. Rosa Casimiro Lau\nDra. Yuriko Zuñica Lavado", "8am - 3pm", R.drawable.ic_med_general),
            Especialidad("Neumología", "Dra. Yessica Montoya Caldas\nDr. Walter Centurion Jaramillo", "10am - 4pm", R.drawable.ic_cardiologia),
            Especialidad("Neurocirugía", "Dr. Willy Caballero Crados", "9am - 1pm", R.drawable.ic_neurologia),
            Especialidad("Neurología", "Dr. Rosnel Valdivieso Velarde", "9am - 3pm", R.drawable.ic_neurologia),
            Especialidad("Nutrición", "Lic. Carmela Carbajal", "10am - 2pm", R.drawable.ic_nutricion),
            Especialidad("Odontología", "Dr. Pedro Cipriano Alegre\nDra. Carolina Acuña Calderon", "9am - 4pm", R.drawable.ic_odonto),
            Especialidad("Cirugía Maxilofacial", "Dr. Julio Robles Zanelli", "8am - 12pm", R.drawable.ic_cirugia),
            Especialidad("Otorrino", "Dr. Jorge Bonilla Vargas", "8am - 1pm", R.drawable.ic_otorrino),
            Especialidad("Oftalmología", "Dra. Anita Vigo Arroyo", "8am - 12pm", R.drawable.ic_oftalmologia),
            Especialidad("Pediatría", "Dr. Marcos Vasquez Tantas\nDr. Jaime Cabrera Pereda", "8am - 3pm", R.drawable.ic_pediatria),
            Especialidad("Psicología", "Lic. Astrid Manrique Marron\nLic. Luz Vasquez Burcos", "10am - 5pm", R.drawable.ic_psicologia),
            Especialidad("Psiquiatría", "Dra. Celmira Lazaro Loyola", "10am - 2pm", R.drawable.ic_psiquiatria),
            Especialidad("Reumatología", "Dr. Frank Ocaña Vasquez", "9am - 2pm", R.drawable.ic_reumatologia),
            Especialidad("Urología", "Dr. Carlos Morales Flores\nDr. Luis Pascual Plasencia\nDr. Jose Acosta Fuentes", "9am - 3pm", R.drawable.ic_urologia),
            // Ya existentes:
            Especialidad("Cardiología", "Dra. López", "10am - 4pm", R.drawable.ic_cardiologia),
            Especialidad("Ginecología", "Dra. Ramírez", "9am - 3pm", R.drawable.ic_ginecologia)
        )

        recyclerView = findViewById(R.id.recyclerEspecialidades)
        adapter = EspecialidadAdapter(especialidades)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

}