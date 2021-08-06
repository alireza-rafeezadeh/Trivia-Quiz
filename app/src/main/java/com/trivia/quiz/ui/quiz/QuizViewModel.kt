package com.trivia.quiz.ui.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trivia.quiz.data.repository.quiz.QuizRepository
//import com.trivia.quiz.ui.data.repository.quiz.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    fun getQuestions() {
        viewModelScope.launch (Dispatchers.IO) {
            val result = quizRepository.getQuestions()
            Log.i("questions_tg", "getQuestions: ${result}")
        }
    }
}