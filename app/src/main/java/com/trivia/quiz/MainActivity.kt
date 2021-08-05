package com.trivia.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.trivia.quiz.ui.data.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun initDB() {
        lifecycleScope.launch(Dispatchers.IO) {

            try {

                val db =
                    Room.databaseBuilder(this@MainActivity, AppDatabase::class.java, "TriviaDB.db")
                        .createFromAsset("TriviaDB.db")
                        .build()
//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).build()

                val questions = db.questionDao().getAll()
                Log.i("questions_tag", "onCreate: ${questions.toString()}")

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "${questions[0].toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {
                Log.i("db_error", "onCreate: ${e.message}")
            }
        }
    }
}