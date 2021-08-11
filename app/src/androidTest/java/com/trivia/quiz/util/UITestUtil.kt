package com.trivia.quiz.util

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import com.trivia.quiz.R
import com.trivia.quiz.ui.start.StartFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
object UITestUtil {
    inline fun <reified T : Fragment> launchFragment(navController: TestNavHostController, @IdRes current : Int) {
        launchFragmentInHiltContainer<T> {
            navController.apply {
                setGraph(R.navigation.navigation_main)
                setCurrentDestination(current)
            }
            Navigation.setViewNavController(requireView(), navController)
        }
    }
}