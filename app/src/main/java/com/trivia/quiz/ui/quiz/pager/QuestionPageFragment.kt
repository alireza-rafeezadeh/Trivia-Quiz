package com.trivia.quiz.ui.quiz.pager

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.trivia.quiz.Question
import com.trivia.quiz.R
import com.trivia.quiz.databinding.FragmentQuestionPageBinding
import com.trivia.quiz.domain.quiz.AnswerStat
import com.trivia.quiz.domain.quiz.Blank
import com.trivia.quiz.domain.quiz.Correct
import com.trivia.quiz.domain.quiz.InCorrect
import com.trivia.quiz.ui.QuizSharedViewModel
import com.trivia.quiz.util.correctAnswerIndex
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionPageFragment(
    var question: Question,
    val questionNumber: Int,
    val onCompleteTimer: () -> Unit
) : ViewBindingFragment<FragmentQuestionPageBinding>() {

    override val bindingInflater:
                (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuestionPageBinding
        get() = FragmentQuestionPageBinding::inflate
//    lateinit var answers: MutableList<Answer>

    lateinit var timer: CountDownTimer
    lateinit var userAnswer: AnswerStat
    lateinit var adapter: AnswerRVAdapter
    var timeToAnswer: Long = 10000
    var additionalTime: Long = 0
    var progressT: Long = 0

    private val sharedViewModel: QuizSharedViewModel by activityViewModels()
    private val viewModel: QuestionViewModel by viewModels()

//    @Inject
//    lateinit var quizResult: QuizResult

    override fun setup() {
        disableButtons()
        setOnClickListeners()
        initTitle()
        initRecyclerView()
    }

    private fun disableButtons() {
        if (sharedViewModel.extraPowers.hasAddTenSeconds) {
            disableButton(binding.addTenSecondsButton)
        }
        if (sharedViewModel.extraPowers.hasSkippedOneQuestion) {
            disableButton(binding.skipQuestion)
        }
        if (sharedViewModel.extraPowers.hasRemovedTwoAnsers) {
            disableButton(binding.removeTwoAnswers)
        }
    }

    private fun disableButton(button: MaterialButton) {
        button.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.nobel)
    }

    private fun initTitle() {
        viewModel.getShuffledAnswers(question)
            .correctAnswerIndex().also {
                userAnswer = Blank(it)
            }
        binding.questionTextView.text = question.question_title
    }

    override fun onResume() {
        super.onResume()
        initCountDownTimer()
        disableButtons()
    }

    private fun initCountDownTimer() {
        timer = object : CountDownTimer(timeToAnswer, 1000) {
            override fun onTick(progress: Long) {
                progressT = progress
                val v = (progress / timeToAnswer.toFloat()) * 100f
                binding.progressBar.progress = v.toInt()
            }

            override fun onFinish() {
                sharedViewModel.userAnswers[questionNumber] = userAnswer
                onCompleteTimer()
            }

        }

        timer.start()
    }

    private fun initRecyclerView() {
        adapter = AnswerRVAdapter(viewModel.answers) { userSelectedIndex ->
            viewModel.answers.correctAnswerIndex().also { correctIndex ->
                if (userSelectedIndex == correctIndex) {
                    userAnswer = Correct(correctIndex)
                } else {
                    userAnswer = InCorrect(userSelectedIndex, correctIndex)
                }
            }

        }
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

        binding.nextButton.setOnClickListener {
            timer.cancel()
            sharedViewModel.userAnswers[questionNumber] = userAnswer
            onCompleteTimer()
        }
        binding.skipQuestion.setOnClickListener {
            if (!sharedViewModel.extraPowers.hasSkippedOneQuestion) {
                /*sharedViewModel.userAnswers[questionNumber] = QuizResult2.Skipped
                onCompleteTimer()*/
                disableButton(binding.skipQuestion)
                sharedViewModel.extraPowers.hasSkippedOneQuestion = true
                question = sharedViewModel.substituteQuestion
                timer.cancel()
                initCountDownTimer()
                initTitle()
                initRecyclerView()
            }
        }

        binding.removeTwoAnswers.setOnClickListener {
            if (!sharedViewModel.extraPowers.hasRemovedTwoAnsers) {
                disableButton(binding.removeTwoAnswers)
                adapter.removeTwoAnswers()
                sharedViewModel.extraPowers.hasRemovedTwoAnsers = true
            }
        }

        binding.addTenSecondsButton.setOnClickListener {
            if (!sharedViewModel.extraPowers.hasAddTenSeconds) {
                disableButton(binding.addTenSecondsButton)
                timer.cancel()
                timeToAnswer = progressT + 10000
                initCountDownTimer()
                sharedViewModel.extraPowers.hasAddTenSeconds = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::timer.isInitialized) {
            timer.cancel()
        }
    }

}