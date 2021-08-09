package com.trivia.quiz.ui.quiz.pager

import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.trivia.quiz.Question
import com.trivia.quiz.databinding.FragmentQuestionPageBinding
import com.trivia.quiz.domain.quiz.Answer
import com.trivia.quiz.domain.quiz.AnswerStat
import com.trivia.quiz.domain.quiz.QuizResult
import com.trivia.quiz.domain.quiz.QuizResult2
import com.trivia.quiz.ui.QuizSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.handleCoroutineException
import javax.inject.Inject

@AndroidEntryPoint
class QuestionPageFragment(
    val question: Question,
    val questionNumber: Int,
    val onCompleteTimer: () -> Unit
) : ViewBindingFragment<FragmentQuestionPageBinding>() {

    override val bindingInflater:
                (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuestionPageBinding
        get() = FragmentQuestionPageBinding::inflate
    lateinit var answers: MutableList<Answer>

    lateinit var timer: CountDownTimer
    var userAnswer = false
    lateinit var userAnswer2 : AnswerStat
    lateinit var adapter: AnswerRVAdapter
    var timeToAnswer: Long = 10000
    var additionalTime: Long = 0
    var progressT: Long = 0

    private val sharedViewModel: QuizSharedViewModel by activityViewModels()


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

        answers.indexOfFirst { ans ->
            ans.isCorrect
        }.also {
            userAnswer2 = QuizResult2.Blank(it)
        }
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
        timer = object : CountDownTimer(timeToAnswer, 1000) {
            override fun onTick(progress: Long) {
                progressT = progress
                val v = (progress / timeToAnswer.toFloat()) * 100f
                binding.progressBar.progress = v.toInt()
            }

            override fun onFinish() {
                quizResult.results.add(userAnswer)
                sharedViewModel.userAnswers[questionNumber] = userAnswer2
                Log.i("<<radio_tg>>", "setOnClickListeners: $quizResult")
                onCompleteTimer()
            }

        }

        timer.start()
    }

    private fun initRecyclerView() {
        adapter = AnswerRVAdapter(answers) { userSelectedIndex ->
            //TODO : make this extensiom funciton
            answers.indexOfFirst { ans ->
                ans.isCorrect
            }.also { correctIndex ->
                if( userSelectedIndex == correctIndex ) {
                    userAnswer2 = QuizResult2.Correct(correctIndex)
                } else {
                    userAnswer2 = QuizResult2.InCorrect(userSelectedIndex , correctIndex)
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
            //TODO: make it a function
            quizResult.results.add(userAnswer)
            sharedViewModel.userAnswers[questionNumber] = userAnswer2
            Log.i("<<radio_tg>>", "setOnClickListeners: $quizResult")
            onCompleteTimer()
        }
        binding.skipQuestion.setOnClickListener {
            if (!sharedViewModel.hasSkippedOneQuestion) {
                /*sharedViewModel.userAnswers[questionNumber] = QuizResult2.Skipped
                sharedViewModel.hasSkippedOneQuestion = true
                onCompleteTimer()*/
            }
        }

        binding.removeTwoAnswers.setOnClickListener {
            if (!sharedViewModel.hasRemovedTwoAnsers) {
                adapter.removeTwoAnswers()
                sharedViewModel.hasRemovedTwoAnsers = true
            }
        }

        binding.addTenSecondsButton.setOnClickListener {
            if (!sharedViewModel.hasAddTenSeconds) {
                timer.cancel()
                timeToAnswer = progressT + 10000
                initCountDownTimer()
                sharedViewModel.hasAddTenSeconds = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

}