package com.trivia.quiz.ui

import androidx.lifecycle.ViewModel
import com.trivia.quiz.Question
import com.trivia.quiz.domain.Constants
import com.trivia.quiz.domain.quiz.AnswerStat
import com.trivia.quiz.domain.quiz.ExtraPowers
import com.trivia.quiz.domain.quiz.QuizResult2

//TODO ...
class QuizSharedViewModel : ViewModel() {

    var extraPowers = ExtraPowers()
    lateinit var substituteQuestion: Question
    var userAnswers: MutableList<AnswerStat> = MutableList(Constants.QUESTIONS_SIZE) {
        QuizResult2.UnViewed
    }

    fun resetStatesForNewQuiz() {
        extraPowers.reset()
        userAnswers = MutableList(Constants.QUESTIONS_SIZE) {
            QuizResult2.UnViewed
        }
    }
}