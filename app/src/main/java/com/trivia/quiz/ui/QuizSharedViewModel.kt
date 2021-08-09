package com.trivia.quiz.ui

import androidx.lifecycle.ViewModel
import com.trivia.quiz.domain.Constants
import com.trivia.quiz.domain.quiz.QuizResult2


class QuizSharedViewModel : ViewModel() {

    var hasSkippedOneQuestion = false
    var hasRemovedTwoAnsers = false
    var hasAddTenSeconds = false

    var userAnswers : MutableList<QuizResult2> = MutableList(Constants.QUESTIONS_SIZE) {
        QuizResult2.Blank
    }
}