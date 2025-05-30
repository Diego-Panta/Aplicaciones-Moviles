package com.example.quiz_uns_panta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz_uns_panta.databinding.ItemScoreBinding

class ScoreAdapter(private val scores: List<UserScore>, private val startRank: Int) :
    RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    inner class ScoreViewHolder(private val binding: ItemScoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(score: UserScore, position: Int) {
            binding.tvName.text = score.name
            binding.tvScore.text = "Puntaje: ${score.score}"
            binding.tvRankPosition.text = (startRank + position).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val binding = ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(scores[position], position)
    }

    override fun getItemCount(): Int = scores.size
}