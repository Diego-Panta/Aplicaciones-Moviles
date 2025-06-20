package com.example.san_pedrito_app.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.san_pedrito_app.model.Participante
import com.example.san_pedrito_app.model.SanPedritoRepository

class SanPedritoViewModel : ViewModel() {
    fun registrarParticipante(
        nombres: String,
        apellidos: String,
        codigo: String,
        dni: String,
        correo: String,
        telefono: String,
        tipo: String
    ): Boolean {
        val participante = Participante(
            nombres = nombres,
            apellidos = apellidos,
            codigo = codigo,
            dni = dni,
            correo = correo,
            telefono = telefono,
            tipo = tipo,
            fechaRegistro = System.currentTimeMillis().toString()
        )

        return SanPedritoRepository.registrarParticipante(participante)
    }
}

class SanPedritoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SanPedritoViewModel::class.java)) {
            return SanPedritoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}