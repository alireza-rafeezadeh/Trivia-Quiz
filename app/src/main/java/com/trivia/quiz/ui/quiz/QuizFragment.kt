package com.trivia.quiz.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.trivia.quiz.Question
import com.trivia.quiz.databinding.FragmentQuizBinding
import com.trivia.quiz.ui.quiz.pager.QuestionPagerAdapter
import com.trivia.quiz.ui.quiz.pager.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : ViewBindingFragment<FragmentQuizBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuizBinding
        get() = FragmentQuizBinding::inflate
    private val viewModel: QuizViewModel by viewModels()

    override fun setup() {

        // The pager adapter, which provides the pages to the view pager widget.


        initObservers()
        viewModel.getQuestions()
    }

    private fun initObservers() {
        viewModel.questionsLiveData.observe(viewLifecycleOwner, {
            initViewPager(it)
        })
    }

    private fun initViewPager(list: List<Question>) {
        val pagerAdapter = QuestionPagerAdapter(requireActivity(),list)
        binding.pager.adapter = pagerAdapter
    }
}