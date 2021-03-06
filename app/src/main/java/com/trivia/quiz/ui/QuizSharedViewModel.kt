package com.trivia.quiz.ui

import androidx.lifecycle.ViewModel
import com.trivia.quiz.Question
import com.trivia.quiz.domain.Constants
import com.trivia.quiz.domain.quiz.AnswerStat
import com.trivia.quiz.domain.quiz.ExtraPowers
import com.trivia.quiz.domain.quiz.AnswerStatus
import com.trivia.quiz.domain.quiz.UnViewed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizSharedViewModel @Inject constructor() : ViewModel() {

    var extraPowers = ExtraPowers()
    lateinit var substituteQuestion: Question
    var userAnswers: MutableList<AnswerStat> = MutableList(Constants.QUESTIONS_SIZE) {
        UnViewed
    }

    fun resetStatesForNewQuiz() {
        extraPowers.reset()
        userAnswers = MutableList(Constants.QUESTIONS_SIZE) {
            UnViewed
        }
    }
}