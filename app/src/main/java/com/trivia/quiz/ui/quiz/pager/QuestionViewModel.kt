package com.trivia.quiz.ui.quiz.pager

import androidx.lifecycle.ViewModel
import com.trivia.quiz.Question
import com.trivia.quiz.domain.quiz.Answer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor() : ViewModel() {

    lateinit var answers: MutableList<Answer>

    fun getShuffledAnswers(question : Question): MutableList<Answer> {
        answers =
            mutableListOf(
                Answer(question.correct_answer, true),
                Answer(question.answer1),
                Answer(question.answer2),
                Answer(question.answer3)
            )
        answers.shuffle()
        return answers
    }

}