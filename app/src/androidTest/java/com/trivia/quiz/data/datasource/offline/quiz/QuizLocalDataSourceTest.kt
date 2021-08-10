package com.trivia.quiz.data.datasource.offline.quiz

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.trivia.quiz.QuestionDao
import com.trivia.quiz.data.room.AppDatabase
import com.trivia.quiz.domain.Constants
import com.trivia.quiz.util.AndroidAppCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class QuizLocalDataSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = AndroidAppCoroutineRule()

    private lateinit var questionDao: QuestionDao
    private lateinit var database: AppDatabase
    private lateinit var quizLocalDataSource: QuizLocalDataSource

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.databaseBuilder(context, AppDatabase::class.java, "TriviaDB.db")
            .createFromAsset("TriviaDB.db")
            .build()
        questionDao = database.questionDao()

        quizLocalDataSource = QuizLocalDataSource(questionDao)
    }

    @Test
    fun getQuestions() = runBlockingTest  {
        quizLocalDataSource.getQuestions().also { questions ->
            Truth.assertThat(questions.size).isEqualTo(Constants.QUESTIONS_SIZE)
        }
    }
}