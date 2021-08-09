package com.trivia.quiz.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trivia.quiz.QuestionDao
import com.trivia.quiz.data.room.AppDatabase
import org.junit.After
import com.google.common.truth.Truth.assertThat
import com.trivia.quiz.domain.Constants
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var questionDao: QuestionDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.databaseBuilder(context, AppDatabase::class.java, "TriviaDB.db")
            .createFromAsset("TriviaDB.db")
            .build()
        questionDao = database.questionDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnCertainSize() {

        val questions = questionDao.getRandomRows()
        assertThat(questions.size).isEqualTo(Constants.QUESTIONS_SIZE)
    }

}
