package com.trivia.quiz.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trivia.quiz.databinding.ItemSummaryBinding
import com.trivia.quiz.domain.quiz.QuizResult2


class SummaryRVAdapter(private val userAnswers: MutableList<QuizResult2>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemSummaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).let {
            ItemViewHolder(it)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(userAnswers[position])
    }

    override fun getItemCount(): Int = userAnswers.size

    inner class ItemViewHolder(val binding: ItemSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quizResult2: QuizResult2) {
            with(binding) {
//                questionNumberTextView.text = quizResult2.
            }
        }
    }
}