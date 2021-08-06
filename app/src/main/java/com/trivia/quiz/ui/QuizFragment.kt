package com.trivia.quiz.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.trivia.quiz.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val viewModel: QuizViewModel by viewModels()

}