package com.trivia.quiz.domain.quiz

data class Answers(val answer: MutableList<Answer>)

data class Answer(val description : String, val isCorrect : Boolean = false)

