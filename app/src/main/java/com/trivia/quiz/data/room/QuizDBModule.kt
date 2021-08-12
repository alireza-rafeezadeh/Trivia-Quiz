package com.trivia.quiz.data.room

import android.content.Context
import androidx.room.Room
import com.trivia.quiz.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuizDBModule {

    @Singleton
    @Provides
    fun providesQuizDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "TriviaDB.db")
            .createFromAsset("TriviaDB.db")
            .build()
    }

    @Singleton
    @Provides
    fun providesQuestionDao(appDatabase : AppDatabase) : QuestionDao {
        return appDatabase.questionDao()
    }
}