package com.example.quiz_uns_panta

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuestionManager(context: Context) {
    var preguntas: List<Question>
    var indiceActual = 0
    var puntaje = 0

    init {
        try {
            val json = context.assets.open("preguntas.json").bufferedReader().use { it.readText() }
            val tipo = object : TypeToken<List<Question>>() {}.type
            preguntas = Gson().fromJson(json, tipo)
        } catch (e: Exception) {
            e.printStackTrace()
            preguntas = emptyList()
        }
    }

    fun obtenerPreguntaActual(): Question? {
        return if (indiceActual < preguntas.size) preguntas[indiceActual] else null
    }

    fun verificarRespuesta(respuesta: String): Boolean {
        val correcta = obtenerPreguntaActual()?.respuestaCorrecta == respuesta
        if (correcta) {
            puntaje += 1 + indiceActual
        } else {
            if (puntaje > 0) puntaje -= 1
        }
        // ¡No incrementes aquí!
        return correcta
    }

    fun avanzarPregunta() {
        indiceActual++
    }

    fun haTerminado(): Boolean {
        return indiceActual >= preguntas.size
    }
}