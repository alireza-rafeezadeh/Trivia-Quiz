package com.trivia.quiz.util

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


fun Fragment.handleBackButton(backButtonLambda: () -> Unit) {
    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backButtonLambda()
            }
        }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}