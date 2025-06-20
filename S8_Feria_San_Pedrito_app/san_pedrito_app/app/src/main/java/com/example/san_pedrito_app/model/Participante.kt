package com.example.san_pedrito_app.model

data class Participante(
    val id: String = "",
    val nombres: String = "",
    val apellidos: String = "",
    val codigo: String = "",
    val dni: String = "",
    val correo: String = "",
    val telefono: String = "",
    val tipo: String = "", // "docente" o "alumno"
    val fechaRegistro: String = ""
)