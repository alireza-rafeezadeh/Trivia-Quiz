package com.trivia.quiz.util

import com.trivia.quiz.Question


object QuizMockData {
    fun getQuestions() = listOf(
        Question(
            1, "question title", "correct answer",
            "answer 1", "answer 2", "answer 3"
        ),
        Question(
            1, "question title", "correct answer",
            "answer 1", "answer 2", "answer 3"
        ),
        Question(
            1, "question title", "correct answer",
            "answer 1", "answer 2", "answer 3"
        ),
        Question(
            1, "question title", "correct answer",
            "answer 1", "answer 2", "answer 3"
        )
    )

}