package com.trivia.quiz.ui.quiz

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import com.trivia.quiz.R
import com.trivia.quiz.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class QuizFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<QuizFragment> {
        }
    }

    @Test
    fun displayViewsInUI() {
        onView(withId(R.id.next_button)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.skip_question)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.remove_two_answers)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_ten_seconds_button)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.question_text_view)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.answers_recycler_view)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(ViewMatchers.isDisplayed()))
    }
}