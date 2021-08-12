package com.trivia.quiz.data.datasource.local.quiz

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class QuizLocalDataSourceModule {

    @Singleton
    @Binds
    abstract fun providesQuizLocalDataSource(
        quizLocalDataSource: QuizLocalDataSource
    ): QuizDataSource
}