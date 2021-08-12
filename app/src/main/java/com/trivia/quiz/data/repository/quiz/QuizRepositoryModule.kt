package com.trivia.quiz.data.repository.quiz

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class QuizRepositoryModule {

    @Binds
    @Singleton
    abstract fun providesQuizRepository(
        defaultQuizRepository: DefaultQuizRepository
    ): QuizRepository
}