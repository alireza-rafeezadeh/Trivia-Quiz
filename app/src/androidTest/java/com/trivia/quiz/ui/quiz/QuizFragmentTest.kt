package com.trivia.quiz.ui.quiz

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
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
// TODO: integration test ...
@LargeTest
class QuizFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<QuizFragment> {
        }
    }

    @Test
    fun test() = runBlockingTest {

        delay(6000)
        Espresso.onView(withId(R.id.next_button))
            .perform(ViewActions.click())
        delay(6000)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())

//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
//        delay(300)
//        Espresso.onView(withId(R.id.next_button))
//            .perform(ViewActions.click())
    }
}