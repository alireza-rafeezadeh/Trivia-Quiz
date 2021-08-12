package com.trivia.quiz.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trivia.quiz.Question
import com.trivia.quiz.data.repository.quiz.QuizRepository
import com.trivia.quiz.domain.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private var _questionsLiveData: MutableLiveData<ResultWrapper<List<Question>>> =
        MutableLiveData()
    val questionsLiveData: LiveData<ResultWrapper<List<Question>>> = _questionsLiveData

    fun getQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            quizRepository.getQuestions().also {
                _questionsLiveData.postValue(it)
            }
        }
    }
}