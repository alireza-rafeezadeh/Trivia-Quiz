package com.trivia.quiz.ui.quiz.pager

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.trivia.quiz.Question
import com.trivia.quiz.R
import com.trivia.quiz.databinding.FragmentQuestionPageBinding
import com.trivia.quiz.domain.quiz.Answer
import com.trivia.quiz.domain.quiz.Answers


class QuestionPageFragment(
    val question: Question,
    val questionNumber : Int
) : ViewBindingFragment<FragmentQuestionPageBinding>() {
    override val bindingInflater:
                (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuestionPageBinding
        get() = FragmentQuestionPageBinding::inflate

    lateinit var answers: MutableList<Answer>
    override fun setup() {
        answers =
            mutableListOf(
                Answer(question.correct_answer, true),
                Answer(question.answer1),
                Answer(question.answer2),
                Answer(question.answer3)
            )
        answers.shuffle()

        binding.questionTextView.text = question.question_title
        binding.answer1RadioButton.text = answers[1].description
        binding.answer2RadioButton.text = answers[2].description
        binding.answer3RadioButton.text = answers[3].description
        binding.answer4RadioButton.text = answers[3].description

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.answersRadioGroup.setOnCheckedChangeListener { radioGroup, index ->
//            if (answers[index].isCorrect) {
//                // QuizResult.getInstance().
//            }
            Log.i("<<radio_tg>>", "setOnClickListeners: $index")
        }
    }

}