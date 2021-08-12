package com.trivia.quiz.util

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.trivia.quiz.domain.quiz.Answer

fun Fragment.handleBackButton(backButtonLambda: () -> Unit) {
    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backButtonLambda()
            }
        }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}

fun MutableList<Answer>.correctAnswerIndex() = indexOfFirst { it.isCorrect }

val Any?.exhaustive get() = Unit
