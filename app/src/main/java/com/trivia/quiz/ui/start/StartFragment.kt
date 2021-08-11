package com.trivia.quiz.ui.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.trivia.quiz.R
import com.trivia.quiz.databinding.FragmentStartBinding
import com.trivia.quiz.ui.quiz.pager.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : ViewBindingFragment<FragmentStartBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStartBinding
        get() = FragmentStartBinding::inflate

    override fun setup() {
        binding.actionButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }
    }
}