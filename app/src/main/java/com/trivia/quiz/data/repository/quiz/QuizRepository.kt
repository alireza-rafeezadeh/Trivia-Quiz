package com.trivia.quiz.data.repository.quiz

import com.trivia.quiz.Question

interface QuizRepository {
    suspend fun getQuestions() : List<Question>
}