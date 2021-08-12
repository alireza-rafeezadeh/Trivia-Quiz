package com.trivia.quiz.data.datasource.offline.quiz

import com.trivia.quiz.Question
import com.trivia.quiz.domain.ResultWrapper

interface QuizDataSource {
    suspend fun getQuestions() : ResultWrapper<List<Question>>
}