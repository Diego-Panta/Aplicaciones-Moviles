package com.example.san_pedrito_app.model

object SanPedritoRepository {
    private val participantes = mutableListOf<Participante>()

    fun registrarParticipante(participante: Participante): Boolean {
        return participantes.add(participante)
    }

    fun obtenerParticipantes(): List<Participante> {
        return participantes.toList()
    }
}