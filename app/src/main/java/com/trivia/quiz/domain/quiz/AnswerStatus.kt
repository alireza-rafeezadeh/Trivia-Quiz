package com.trivia.quiz.domain.quiz

sealed interface AnswerStat

sealed class CorrectAnswerHolder(val correctChoice: Int, val title: String) : AnswerStat, IAnswer

interface IAnswer {
    fun getCorrectAnswerString(): String
}


sealed class AnswerStatus(
    val userChoice: Int,
    private val correct: Int,
    private val titleName: String
) : CorrectAnswerHolder(correct, titleName)

data class Correct(val choiceNumber: Int) : AnswerStatus(choiceNumber, choiceNumber, "Correct") {
    override fun getCorrectAnswerString() = (choiceNumber + 1).toString()
}

data class InCorrect(val choiceNumber: Int, val correctAnswer: Int) :
    AnswerStatus(choiceNumber, correctAnswer, "Incorrect") {
    override fun getCorrectAnswerString() = (correctAnswer + 1).toString()
    fun getUserAnswerString() = (choiceNumber + 1).toString()
}

data class Blank(val correctAnswer: Int) : CorrectAnswerHolder(correctAnswer, "Blank") {
    override fun getCorrectAnswerString() = (correctAnswer + 1).toString()
}

object UnViewed : AnswerStat

