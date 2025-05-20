package com.example.quiz_uns_panta

data class Question(
    val pregunta: String,
    val opciones: List<String>,
    val respuestaCorrecta: String
)