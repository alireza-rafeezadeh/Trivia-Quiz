package com.trivia.quiz

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.trivia.quiz.domain.Constants

@Dao
interface QuestionDao {

    @Query("SELECT * FROM Questions ORDER BY RANDOM() LIMIT ${Constants.QUESTIONS_SIZE}")
    fun getRandomRows(): List<Question>
}
