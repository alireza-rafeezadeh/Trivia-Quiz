package com.trivia.quiz.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.trivia.quiz.R
import com.trivia.quiz.databinding.FragmentSummaryBinding
import com.trivia.quiz.domain.quiz.*
import com.trivia.quiz.ui.QuizSharedViewModel
import com.trivia.quiz.ui.quiz.pager.ViewBindingFragment
import com.trivia.quiz.util.handleBackButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : ViewBindingFragment<FragmentSummaryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSummaryBinding
        get() = FragmentSummaryBinding::inflate

    private val sharedViewModel: QuizSharedViewModel by activityViewModels()

    override fun setup() {
        handleBackButton {
            Toast.makeText(
                requireContext(),
                getString(R.string.unable_to_go_back),
                Toast.LENGTH_SHORT
            ).show()
        }
        setClickListeners()
        initRecyclerView()
    }

    private fun setClickListeners() {
        binding.startOverButton.setOnClickListener {
            sharedViewModel.resetStatesForNewQuiz()
            findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
        }
    }

    private fun initRecyclerView() {
        SummaryRVAdapter(sharedViewModel.userAnswers).also {
            binding.summaryRVAdapter.adapter = it
        }
    }
}