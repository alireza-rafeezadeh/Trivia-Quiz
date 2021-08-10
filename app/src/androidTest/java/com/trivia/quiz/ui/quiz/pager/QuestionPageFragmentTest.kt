package com.trivia.quiz.ui.quiz.pager

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.trivia.quiz.Question
import com.trivia.quiz.ui.quiz.QuizFragment
import com.trivia.quiz.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
// TODO: integration test ...
@LargeTest
class QuestionPageFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        val quesiton = Question(
            1,"","ans0","ans1","ans2","ans3"
        )
        val frag = QuestionPageFragment(quesiton,0) {

        }
        launchFragmentInHiltContainer<QuestionPageFragment> {
        }
    }

    @Test
    fun test() {

    }
}