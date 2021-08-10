package com.trivia.quiz.ui.quiz

import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.trivia.quiz.Question
import com.trivia.quiz.R
import com.trivia.quiz.databinding.FragmentQuizBinding
import com.trivia.quiz.domain.Constants
import com.trivia.quiz.ui.QuizSharedViewModel
import com.trivia.quiz.ui.quiz.pager.QuestionPagerAdapter
import com.trivia.quiz.ui.quiz.pager.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : ViewBindingFragment<FragmentQuizBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizBinding
        get() = FragmentQuizBinding::inflate
    private val viewModel: QuizViewModel by viewModels()
    val sharedViewModel: QuizSharedViewModel by activityViewModels()

    override fun setup() {

        // The pager adapter, which provides the pages to the view pager widget.


        initObservers()
        viewModel.getQuestions()
    }

    private fun initObservers() {
        viewModel.questionsLiveData.observe(viewLifecycleOwner, {
            sharedViewModel.substituteQuestion = it[Constants.QUESTIONS_SIZE - 1]
            initViewPager(it)
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