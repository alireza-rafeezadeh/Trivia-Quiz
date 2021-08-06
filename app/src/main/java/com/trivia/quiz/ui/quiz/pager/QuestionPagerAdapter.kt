package com.trivia.quiz.ui.quiz.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.trivia.quiz.Question


class QuestionPagerAdapter(fa : FragmentActivity,val list : List<Question>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int  = 6
    override fun createFragment(position: Int): Fragment = QuestionPageFragment(list[position], position)
}