package com.trivia.quiz.ui

import androidx.lifecycle.ViewModel
import com.trivia.quiz.data.repository.quiz.QuizRepository
//import com.trivia.quiz.ui.data.repository.quiz.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    fun getQuestions() {

    }
}