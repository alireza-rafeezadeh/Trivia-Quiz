package com.trivia.quiz.ui.start

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import com.trivia.quiz.R
import com.trivia.quiz.util.UITestUtil
import com.trivia.quiz.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@ExperimentalCoroutinesApi
@MediumTest
class StartFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun navigatesToQuizFragment() {
        TestNavHostController(getApplicationContext()).also {
            UITestUtil.launchFragment(it)
            onView(withId(R.id.action_button)).perform(click())
            Truth.assertThat(it.currentDestination?.id).isEqualTo(R.id.quizFragment)
        }
    }

    @Test
    fun displayViewsInUI() {
        TestNavHostController(getApplicationContext()).also {
            UITestUtil.launchFragment(it)
            onView(withId(R.id.action_button)).check(matches(isDisplayed()))
        }
    }
}