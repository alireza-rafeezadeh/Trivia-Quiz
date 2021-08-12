package com.trivia.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizActivity : AppCompatActivity(R.layout.activity_quiz) {
    override fun onPause() {
        super.onPause()
        finish()
    }
}