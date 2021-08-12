package com.trivia.quiz.ui

import androidx.lifecycle.ViewModel
import com.trivia.quiz.Question
import com.trivia.quiz.domain.Constants
import com.trivia.quiz.domain.quiz.AnswerStat
import com.trivia.quiz.domain.quiz.QuizResult2

//TODO ...
class QuizSharedViewModel : ViewModel() {

    //TODO: make these three a data class
    var hasSkippedOneQuestion = false
    var hasRemovedTwoAnsers = false
    var hasAddTenSeconds = false

    lateinit var substituteQuestion: Question

    var userAnswers: MutableList<AnswerStat> = MutableList(Constants.QUESTIONS_SIZE) {
        QuizResult2.UnViewed
    }

    fun resetStatesForNewQuiz() {
        hasSkippedOneQuestion = false
        hasRemovedTwoAnsers = false
        hasAddTenSeconds = false
        userAnswers = MutableList(Constants.QUESTIONS_SIZE) {
            QuizResult2.UnViewed
        }
    }
}