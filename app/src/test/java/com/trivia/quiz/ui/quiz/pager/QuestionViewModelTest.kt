package com.trivia.quiz.ui.quiz.pager

import com.google.common.truth.Truth
import com.trivia.quiz.Question
import com.trivia.quiz.domain.quiz.Answer
import org.junit.Test

import org.junit.Assert.*

class QuestionViewModelTest {

    @Test
    fun getAnswers() {

        val viewModel = QuestionViewModel()
        val question = Question(
            1, "question", "correct ans",
            "ans1", "ans2", "ans3"
        )
        val answers =
            mutableListOf(
                Answer(question.correct_answer, true),
                Answer(question.answer1),
                Answer(question.answer2),
                Answer(question.answer3)
            )

        Truth.assertThat(answers).isNotEqualTo(viewModel.getShuffledAnswers(question))

    }
}