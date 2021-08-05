package com.trivia.quiz

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    fun getAll(): List<Question>

    @Insert
    fun insertAll(vararg questions: Question)

    @Delete
    fun delete(question: Question)
}
