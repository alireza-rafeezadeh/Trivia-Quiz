package com.trivia.quiz.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trivia.quiz.Question
import com.trivia.quiz.QuestionDao

@Database(entities = [Question::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}