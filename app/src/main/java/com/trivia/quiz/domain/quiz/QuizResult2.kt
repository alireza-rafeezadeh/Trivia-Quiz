package com.trivia.quiz.domain.quiz

//TODO Improve this
sealed class QuizResult2() {
    data class Correct(val choiceNumber: Int) : QuizResult2()
    data class InCorrect(val choiceNumber: Int) : QuizResult2()
    object Blank : QuizResult2()
    object Skipped : QuizResult2()
}
