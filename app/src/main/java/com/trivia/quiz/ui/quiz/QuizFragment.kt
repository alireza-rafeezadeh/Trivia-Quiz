package com.trivia.quiz.ui.quiz

import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.trivia.quiz.Question
import com.trivia.quiz.R
import com.trivia.quiz.databinding.FragmentQuizBinding
import com.trivia.quiz.domain.Constants
import com.trivia.quiz.domain.Error
import com.trivia.quiz.domain.InProgress
import com.trivia.quiz.domain.Success
import com.trivia.quiz.ui.QuizSharedViewModel
import com.trivia.quiz.ui.quiz.pager.QuestionPagerAdapter
import com.trivia.quiz.ui.quiz.pager.ViewBindingFragment
import com.trivia.quiz.ui.summary.exhaustive
import com.trivia.quiz.util.handleBackButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : ViewBindingFragment<FragmentQuizBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizBinding
        get() = FragmentQuizBinding::inflate
    private val viewModel: QuizViewModel by viewModels()
    private val sharedViewModel: QuizSharedViewModel by activityViewModels()

    override fun setup() {

        // The pager adapter, which provides the pages to the view pager widget.

        handleBackButton {
            Toast.makeText(
                requireContext(),
                getString(R.string.unable_to_go_back),
                Toast.LENGTH_SHORT
            ).show()
        }

        initObservers()
        viewModel.getQuestions()
    }

    private fun initObservers() {
        viewModel.questionsLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Error -> {
                    Toast.makeText(requireContext(), it.exception, Toast.LENGTH_SHORT).show()
                }
                InProgress -> {

                }
                is Success -> {
                    sharedViewModel.substituteQuestion = it.data[Constants.QUESTIONS_SIZE - 1]
                    initViewPager(it.data)
                }
            }.exhaustive
        })
    }

    private fun initViewPager(list: List<Question>) {
        val pagerAdapter = QuestionPagerAdapter(requireActivity(), list) {
            // TODO: assume there is no skipped question yet and also make it more kotlin style
            if (binding.pager.currentItem >= Constants.QUESTIONS_SIZE - 2) {
                findNavController().navigate(R.id.action_quizFragment_to_summaryFragment)
            } else {
                binding.pager.currentItem += 1
            }
        }
        binding.pager.isUserInputEnabled = false
        binding.pager.adapter = pagerAdapter
    }
}