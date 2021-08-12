package com.trivia.quiz.ui.quiz

import com.trivia.quiz.Question
import com.trivia.quiz.data.repository.quiz.QuizRepository
import com.trivia.quiz.domain.Success
import com.trivia.quiz.util.QuizMockData


class FakeQuizRepository : QuizRepository {
    override suspend fun getQuestions() = Success(QuizMockData.getQuestions())
}