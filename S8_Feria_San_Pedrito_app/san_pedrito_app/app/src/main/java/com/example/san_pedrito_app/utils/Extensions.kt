package com.example.san_pedrito_app.utils

class Extensions {

    fun String.toTitleCase(): String {
        return this.split(" ").joinToString(" ") { word ->
            word.replaceFirstChar { it.uppercase() }
        }
    }

}