package com.trivia.quiz.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.trivia.quiz.databinding.FragmentSummaryBinding
import com.trivia.quiz.domain.quiz.QuizResult2
import com.trivia.quiz.ui.QuizSharedViewModel
import com.trivia.quiz.ui.quiz.pager.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : ViewBindingFragment<FragmentSummaryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSummaryBinding
        get() = FragmentSummaryBinding::inflate

    private val sharedViewModel: QuizSharedViewModel by activityViewModels()

    override fun setup() {

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

        initRecyclerView()
    }

    fun initRecyclerView() {
        val adapter = SummaryRVAdapter(sharedViewModel.userAnswers)
        binding.summaryRVAdapter.adapter = adapter
    }
}