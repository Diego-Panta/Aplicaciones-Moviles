package com.example.quiz_uns_panta

object FakeUserRepository {
    fun getFakeUserScores(): List<UserScore> {
        return listOf(
            UserScore("Ana Pérez", (60..100).random()),
            UserScore("Luis Gómez", (60..100).random()),
            UserScore("Carlos Ruiz", (60..100).random()),
            UserScore("Laura Torres", (60..100).random()),
            UserScore("Pedro Salas", (60..100).random()),
            UserScore("Sofía Mendoza", (60..100).random()),
            UserScore("Javier Lima", (60..100).random())
        ).sortedByDescending { it.score }
    }
}