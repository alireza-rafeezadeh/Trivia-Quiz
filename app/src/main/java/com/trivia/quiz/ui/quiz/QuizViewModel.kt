package com.trivia.quiz.ui.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trivia.quiz.Question
import com.trivia.quiz.data.repository.quiz.QuizRepository
//import com.trivia.quiz.ui.data.repository.quiz.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    var questionsLiveData : MutableLiveData<List<Question>> = MutableLiveData()

    fun getQuestions() {
        viewModelScope.launch (Dispatchers.IO) {
            quizRepository.getQuestions().also {
                questionsLiveData.postValue(it)
            }
        }
    }
}