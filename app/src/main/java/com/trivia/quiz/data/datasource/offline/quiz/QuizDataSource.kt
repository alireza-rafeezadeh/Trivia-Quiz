package com.trivia.quiz.data.datasource.offline.quiz

import com.trivia.quiz.Question

interface QuizDataSource {
    suspend fun getQuestions() : List<Question>
}