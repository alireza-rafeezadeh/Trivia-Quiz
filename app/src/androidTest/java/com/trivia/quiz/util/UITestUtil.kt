package com.trivia.quiz.util

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import com.trivia.quiz.R
import com.trivia.quiz.ui.start.StartFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
object UITestUtil {
    fun launchFragment(navController: TestNavHostController) {
        launchFragmentInHiltContainer<StartFragment> {
            navController.setGraph(R.navigation.navigation_main)
            navController.setCurrentDestination(R.id.startFragment)
            Navigation.setViewNavController(requireView(), navController)
        }
    }
}