package com.example.quiz_uns_panta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    private val _scores = MutableLiveData<List<UserScore>>()
    val scores: LiveData<List<UserScore>> get() = _scores

    init {
        loadScores()
    }

    private fun loadScores() {
        _scores.value = FakeUserRepository.getFakeUserScores()
    }
}