package com.trivia.quiz.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trivia.quiz.databinding.ItemSummaryBinding
import com.trivia.quiz.domain.quiz.*


class SummaryRVAdapter(private val userAnswers: MutableList<AnswerStat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemSummaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).let {
            ItemViewHolder(it)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position > 0) {
            (holder as ItemViewHolder).bind(userAnswers[position-1])
        } else {
            (holder as ItemViewHolder).bindFirstRow()
        }

    }

    override fun getItemCount(): Int = userAnswers.size

    inner class ItemViewHolder(val binding: ItemSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(answerStat: AnswerStat) {
            with(binding) {
//                questionNumberTextView.text = quizResult2.
                questionNumberTextView.text = adapterPosition.toString()
                when (answerStat) {
                    is Blank -> {
                        userAnswerTextView.text = "-"
                        correctAnswerTextView.text = answerStat.getCorrectAnswerString()
                        answerStatusTextView.text = answerStat.title
                    }
                    is Correct -> {
                        userAnswerTextView.text = answerStat.getCorrectAnswerString()
                        correctAnswerTextView.text = answerStat.getCorrectAnswerString()
                        answerStatusTextView.text = answerStat.title
                    }
                    is InCorrect -> {
                        userAnswerTextView.text = answerStat.getUserAnswerString()
                        correctAnswerTextView.text = answerStat.getCorrectAnswerString()
                        answerStatusTextView.text = answerStat.title
                    }
                    UnViewed -> {
                    }
                }.exhaustive

            }
        }

        fun bindFirstRow() {
            with(binding) {
                questionNumberTextView.text = "#"
                userAnswerTextView.text = "your answer"
                correctAnswerTextView.text = "correct answer"
                answerStatusTextView.text = "status"
            }
        }
    }
}