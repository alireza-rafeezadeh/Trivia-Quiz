package com.trivia.quiz.ui.quiz.pager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trivia.quiz.R
import com.trivia.quiz.databinding.ItemAnswerBinding
import com.trivia.quiz.domain.quiz.Answer


class AnswerRVAdapter(
    private val answers: MutableList<Answer>,
    private val onClick: (index: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var oldSelectedItem = -1
    var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemAnswerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).let {
            AnswerViewHolder(it)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AnswerViewHolder).bindView(answers[position])
//        if (position == oldSelectedItem) {
//            holder.unSelectItem()
//        }
        if (position == selectedItem) {
            holder.selectItem()
        } else {
            holder.unSelectItem()

        }

    }

    override fun getItemCount(): Int = answers.size

    fun removeTwoAnswers() {

        answers.indexOfFirst {
            it.isCorrect
        }.also {
            answers.removeAt((it + 1) % 4)
            answers.removeAt((it + 2) % 4)
        }
        notifyDataSetChanged()
    }

    inner class AnswerViewHolder(private val binding: ItemAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
//                getItem(bindingAdapterPosition)?.let { item -> onClick(item) }

                /*if (oldSelectedItem > 0) {
//                    binding.root.setBackgroundColor(Color.WHITE)
                    notifyItemChanged(oldSelectedItem)
                }
                binding.root.setBackgroundColor(Color.GRAY)
                oldSelectedItem = adapterPosition*/
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
            binding.root.setBackgroundColor(Color.WHITE)
        }

        fun selectItem() {
            binding.root.setBackgroundColor(Color.GRAY)
        }
    }
}