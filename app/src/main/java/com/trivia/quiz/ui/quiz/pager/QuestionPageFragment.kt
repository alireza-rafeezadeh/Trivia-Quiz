package com.trivia.quiz.ui.quiz.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trivia.quiz.Question
import com.trivia.quiz.R
import com.trivia.quiz.databinding.FragmentQuestionPageBinding


class QuestionPageFragment(
    val question: Question
) : ViewBindingFragment<FragmentQuestionPageBinding>() {
    override val bindingInflater:
                (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuestionPageBinding
        get() = FragmentQuestionPageBinding::inflate

    override fun setup() {
        binding.questionTextView.text = question.question_title
    }

}