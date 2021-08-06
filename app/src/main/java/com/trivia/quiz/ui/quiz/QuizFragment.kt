package com.trivia.quiz.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
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
        val pagerAdapter = QuestionPagerAdapter(requireActivity())
        binding.pager.adapter = pagerAdapter
    }
}