package com.trivia.quiz.data.repository.quiz

import com.trivia.quiz.Question
import com.trivia.quiz.domain.ResultWrapper

interface QuizRepository {
    suspend fun getQuestions() : ResultWrapper<List<Question>>
}