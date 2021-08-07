package com.trivia.quiz.data.datasource.offline.quiz

import com.trivia.quiz.Question
import com.trivia.quiz.data.room.AppDatabase
import javax.inject.Inject

class DefaultQuizOfflineDataSource @Inject constructor(
    private val database: AppDatabase
) : QuizOfflineDataSource {
    override suspend fun getQuestions(): List<Question> {
        return database.questionDao().getRandomRows()
    }
}