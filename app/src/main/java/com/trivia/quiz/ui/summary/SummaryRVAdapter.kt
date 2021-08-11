package com.trivia.quiz.ui.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trivia.quiz.databinding.ItemSummaryBinding
import com.trivia.quiz.domain.quiz.AnswerStat
import com.trivia.quiz.domain.quiz.QuizResult2


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
                    is QuizResult2.Blank -> {
                        userAnswerTextView.text = "-"
                        correctAnswerTextView.text = answerStat.getCorrectAnswerString()
                        answerStatusTextView.text = answerStat.title
                    }
                    is QuizResult2.Correct -> {
                        userAnswerTextView.text = answerStat.getCorrectAnswerString()
                        correctAnswerTextView.text = answerStat.getCorrectAnswerString()
                        answerStatusTextView.text = answerStat.title
                    }
                    is QuizResult2.InCorrect -> {
                        userAnswerTextView.text = answerStat.getUserAnswerString()
                        correctAnswerTextView.text = answerStat.getCorrectAnswerString()
                        answerStatusTextView.text = answerStat.title
                    }
                    QuizResult2.UnViewed -> {
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