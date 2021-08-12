package com.trivia.quiz.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.trivia.quiz.R
import com.trivia.quiz.databinding.FragmentSummaryBinding
import com.trivia.quiz.domain.quiz.QuizResult2
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
            Toast.makeText(requireContext(), getString(R.string.unable_to_go_back), Toast.LENGTH_SHORT).show()
        }

        var corrects = 0
        var inCorrects = 0
        var skippeds = 0
        var blanks = 0

        lifecycleScope.launchWhenResumed {
            sharedViewModel.userAnswers.forEach {
                when (it) {
                    is QuizResult2.Correct -> {
                        corrects++
                    }
                    is QuizResult2.Blank -> blanks++
                    is QuizResult2.InCorrect -> inCorrects++
                    QuizResult2.UnViewed -> { }
                }.exhaustive
            }
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
        val adapter = SummaryRVAdapter(sharedViewModel.userAnswers)
        binding.summaryRVAdapter.adapter = adapter
    }
}