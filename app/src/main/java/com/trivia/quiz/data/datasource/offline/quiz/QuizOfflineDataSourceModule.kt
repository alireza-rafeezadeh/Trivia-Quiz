package com.trivia.quiz.data.datasource.offline.quiz

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class QuizOfflineDataSourceModule {

    @Singleton
    @Binds
    abstract fun providesQuizOfflineDataSource(
        quizLocalDataSource: QuizLocalDataSource
    ): QuizDataSource
}