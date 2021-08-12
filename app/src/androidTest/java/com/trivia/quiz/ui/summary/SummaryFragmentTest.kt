package com.trivia.quiz.ui.summary

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import com.trivia.quiz.R
import com.trivia.quiz.util.UITestUtil
import com.trivia.quiz.util.UITestUtil.launchFragment
import com.trivia.quiz.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@ExperimentalCoroutinesApi
@MediumTest
class SummaryFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
    }

    @Test
    fun navigatesToStartFragment() {
        TestNavHostController(ApplicationProvider.getApplicationContext()).also {
            launchFragment<SummaryFragment>(it, R.id.summaryFragment)
            onView(withId(R.id.start_over_button)).perform(click())
            Truth.assertThat(it.currentDestination?.id).isEqualTo(R.id.startFragment)
        }
    }

    @Test
    fun displayViewsInUI() {
        launchFragmentInHiltContainer<SummaryFragment> {  }
        onView(withId(R.id.summaryRVAdapter)).check(matches(isDisplayed()))
        onView(withId(R.id.start_over_button)).check(matches(isDisplayed()))
    }
}