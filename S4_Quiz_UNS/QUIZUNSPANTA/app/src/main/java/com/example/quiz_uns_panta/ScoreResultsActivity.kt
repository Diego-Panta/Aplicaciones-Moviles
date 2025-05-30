package com.example.quiz_uns_panta

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz_uns_panta.databinding.ActivityScoreResultsBinding

class ScoreResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreResultsBinding
    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mostrar puntaje del usuario
        val nombreJugador = intent.getStringExtra("nombre_jugador") ?: "Jugador"
        val puntajeJugador = intent.getIntExtra("puntaje_jugador", 0)
        val btnRestartQuiz: ImageView = findViewById(R.id.btnRestartQuiz)

        btnRestartQuiz.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // opcional para cerrar esta activity
        }

        viewModel.scores.observe(this) { scores ->
            val listaActualizada = scores.toMutableList()
            listaActualizada.add(UserScore(nombreJugador, puntajeJugador))

            val ordenados = listaActualizada.sortedByDescending { it.score }
            
            if (ordenados.size >= 3) {
                binding.tvFirstName.text = ordenados[0].name
                binding.tvSecondName.text = ordenados[1].name
                binding.tvThirdName.text = ordenados[2].name

                val others = ordenados.drop(3)
                binding.recyclerViewOthers.layoutManager = LinearLayoutManager(this)
                binding.recyclerViewOthers.adapter = ScoreAdapter(others, startRank = 4)
            }
        }
    }
}