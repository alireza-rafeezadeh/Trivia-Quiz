package com.trivia.quiz.ui.quiz.pager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.trivia.quiz.R
import com.trivia.quiz.databinding.ItemAnswerBinding
import com.trivia.quiz.domain.quiz.Answer
import com.trivia.quiz.util.correctAnswerIndex


class AnswerRVAdapter(
    private val answers: MutableList<Answer>,
    private val onClick: (index: Int) -> Unit
) : RecyclerView.Adapter<AnswerRVAdapter.AnswerViewHolder>() {

    var selectedItem = -1
    lateinit var wrongAnswers: Set<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemAnswerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).let {
            AnswerViewHolder(it)
        }

    override fun onBindViewHolder(holder: AnswerRVAdapter.AnswerViewHolder, position: Int) {
        holder.bindView(answers[position])
        if (position == selectedItem) {
            holder.selectItem()
        } else {
            holder.unSelectItem()
        }

        if (::wrongAnswers.isInitialized && wrongAnswers.contains(position)) {
            holder.revealWrongAnswer()
        }

    }

    override fun getItemCount(): Int = answers.size

    fun removeTwoAnswers() {

        answers.correctAnswerIndex().also {
            wrongAnswers = setOf((it + 1) % 4, (it + 2) % 4)
        }
        notifyDataSetChanged()
    }

    inner class AnswerViewHolder(private val binding: ItemAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick(adapterPosition)
                selectedItem = adapterPosition
                notifyDataSetChanged()
            }
        }


        fun bindView(answer: Answer) {
            with(binding) {
                answerTextView.text = answer.description
            }
        }

        fun unSelectItem() {
            binding.root.background =
                ContextCompat.getDrawable(binding.root.context, R.drawable.curved_white_bg)
        }

        fun selectItem() {
            binding.root.background =
                ContextCompat.getDrawable(binding.root.context, R.drawable.item_selected_bg)
        }

        fun revealWrongAnswer() {
            binding.wrongAnswerImaheView.visibility = View.VISIBLE
        }
    }
}