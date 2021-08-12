package com.trivia.quiz.ui.quiz

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.trivia.quiz.util.AppCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.trivia.quiz.Question
import com.trivia.quiz.domain.ResultWrapper
import com.trivia.quiz.domain.Success
import com.trivia.quiz.util.QuizMockData
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule

@ExperimentalCoroutinesApi
class QuizViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = AppCoroutineRule()

    lateinit var viewModel: QuizViewModel


    @Before
    fun setUp() {
        viewModel = QuizViewModel(FakeQuizRepository())

    }

    @After
    fun tearDown() {
    }

    @Test
    fun `questions should return success`() = runBlockingTest {
        val observer = Observer<ResultWrapper<List<Question>>> {
            assertThat(viewModel.questionsLiveData.value).isEqualTo(
                Success(QuizMockData.getQuestions())
            )
        }
        viewModel.run {
            questionsLiveData.observeForever(observer)
            getQuestions()
            questionsLiveData.removeObserver(observer)
        }
    }
}