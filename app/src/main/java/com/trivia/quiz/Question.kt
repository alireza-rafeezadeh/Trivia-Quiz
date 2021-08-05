package com.trivia.quiz

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "question_title") val question_title: String?,
    @ColumnInfo(name = "correct_answer") val correct_answer: String?,
    @ColumnInfo(name = "answer1") val answer1: String?,
    @ColumnInfo(name = "answer2") val answer2: String?,
    @ColumnInfo(name = "answer3") val answer3: String?,
)