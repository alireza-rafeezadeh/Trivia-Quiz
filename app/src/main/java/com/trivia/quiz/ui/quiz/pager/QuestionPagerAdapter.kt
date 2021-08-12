package com.trivia.quiz.ui.quiz.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.trivia.quiz.Question
import com.trivia.quiz.domain.Constants


class QuestionPagerAdapter(
    fa: FragmentActivity, val list: List<Question>, private val onCompleteTimer: () -> Unit
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = Constants.QUESTIONS_SIZE
    override fun createFragment(position: Int): Fragment =
        QuestionPageFragment(list[position], position, onCompleteTimer)
}