package com.trivia.quiz.data.repository.quiz

//import com.trivia.quiz.ui.data.datasource.offline.quiz.QuizOfflineDataSource
import com.trivia.quiz.Question
import com.trivia.quiz.data.datasource.offline.quiz.QuizOfflineDataSource
import javax.inject.Inject

class DefaultQuizRepository @Inject constructor(
    private val quizOfflineDataSource: QuizOfflineDataSource
) : QuizRepository {
    override suspend fun getQuestions(): List<Question> {
        return quizOfflineDataSource.getQuestions()
    }
}