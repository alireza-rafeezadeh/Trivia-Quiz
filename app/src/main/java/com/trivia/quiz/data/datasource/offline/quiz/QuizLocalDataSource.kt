package com.trivia.quiz.data.datasource.offline.quiz

import com.trivia.quiz.Question
import com.trivia.quiz.QuestionDao
import javax.inject.Inject

class QuizLocalDataSource @Inject constructor(
    private val dao: QuestionDao
) : QuizDataSource {
    override suspend fun getQuestions(): List<Question> {
        return dao.getRandomRows()
    }
}