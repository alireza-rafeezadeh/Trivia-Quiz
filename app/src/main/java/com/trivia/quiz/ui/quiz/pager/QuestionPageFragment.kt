package com.trivia.quiz.ui.quiz.pager

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import com.trivia.quiz.Question
import com.trivia.quiz.databinding.FragmentQuestionPageBinding
import com.trivia.quiz.domain.quiz.Answer
import com.trivia.quiz.domain.quiz.QuizResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.concurrent.timer

@AndroidEntryPoint
class QuestionPageFragment(
    val question: Question,
    val questionNumber : Int,
    val onCompleteTimer: () -> Unit
) : ViewBindingFragment<FragmentQuestionPageBinding>() {

    override val bindingInflater:
                (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuestionPageBinding
        get() = FragmentQuestionPageBinding::inflate
    lateinit var answers: MutableList<Answer>

    lateinit var timer: CountDownTimer

    @Inject
    lateinit var quizResult: QuizResult

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
        /*binding.answer1RadioButton.text = answers[1].description
        binding.answer2RadioButton.text = answers[2].description
        binding.answer3RadioButton.text = answers[3].description
        binding.answer4RadioButton.text = answers[3].description*/

        initRecyclerView()
        setOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        initCountDownTimer()
    }

    private fun initCountDownTimer() {
        timer = object : CountDownTimer(10000,1000) {
            override fun onTick(progress: Long) {
                val v = (progress / 10000f) * 100f
                binding.progressBar.progress = v.toInt()
            }

            override fun onFinish() {
                onCompleteTimer()
            }

        }

        timer.start()
    }

    private fun initRecyclerView() {
        val adapter = AnswerRVAdapter(answers)
        binding.answersRecyclerView.adapter = adapter
    }

    private fun setOnClickListeners() {
        /*binding.answersRadioGroup.setOnCheckedChangeListener { radioGroup, index ->
//            if (answers[index].isCorrect) {
//                // QuizResult.getInstance().
//            }
            quizResult.results.add(true)
            Log.i("<<radio_tg>>", "setOnClickListeners: $quizResult")
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

}