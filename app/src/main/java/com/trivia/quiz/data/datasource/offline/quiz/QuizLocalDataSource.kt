package com.trivia.quiz.data.datasource.offline.quiz

import com.trivia.quiz.Question
import com.trivia.quiz.QuestionDao
import com.trivia.quiz.domain.Error
import com.trivia.quiz.domain.ResultWrapper
import com.trivia.quiz.domain.Success
import javax.inject.Inject


class QuizLocalDataSource @Inject constructor(
    private val dao: QuestionDao
) : QuizDataSource {
    override suspend fun getQuestions(): ResultWrapper<List<Question>> {
        return try {
            val result = dao.getRandomRows()
            Success(result)
        } catch (exception: Exception) {
            Error(exception.message ?: "Unknown error!")
        }
    }
}