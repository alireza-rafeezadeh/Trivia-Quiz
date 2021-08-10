package com.trivia.quiz.domain.quiz

sealed interface AnswerStat

sealed class CorrectAnswerHolder(val correctChoice : Int, val title : String) : AnswerStat, IAnswer

interface IAnswer {
    fun getCorrectAnswerString() : String
}

//TODO Improve this
sealed class QuizResult2(val userChoice: Int, val correct: Int, val titleName: String) : CorrectAnswerHolder(correct, titleName) {

    data class Correct(val choiceNumber: Int) : QuizResult2(choiceNumber, choiceNumber,"Correct") {
        override fun getCorrectAnswerString() = (choiceNumber + 1).toString()
    }

    data class InCorrect(val choiceNumber: Int, val correct_Choice: Int) : QuizResult2(choiceNumber, correct_Choice,"Incorrect") {
        override fun getCorrectAnswerString() = (correct_Choice + 1).toString()
        fun getUserAnswerString() = (choiceNumber + 1).toString()
    }

    data class Blank(val correctAnswer : Int) : CorrectAnswerHolder(correctAnswer,"Blank") {
        override fun getCorrectAnswerString() = (correctAnswer + 1).toString()
    }

    object UnViewed : AnswerStat
}
