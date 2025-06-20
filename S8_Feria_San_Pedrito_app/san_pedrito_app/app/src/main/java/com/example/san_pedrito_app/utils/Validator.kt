package com.example.san_pedrito_app.utils

object Validator {
    fun validarNombre(nombre: String): Boolean {
        return nombre.isNotBlank() && nombre.length >= 3
    }

    fun validarCodigo(codigo: String): Boolean {
        return codigo.isNotBlank() && codigo.length in 6..10
    }

    fun validarDNI(dni: String): Boolean {
        return dni.length == 8 && dni.all { it.isDigit() }
    }

    fun validarCorreo(correo: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }

    fun validarTelefono(telefono: String): Boolean {
        return telefono.length == 9 && telefono.all { it.isDigit() }
    }
}