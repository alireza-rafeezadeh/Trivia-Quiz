package com.trivia.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launch(Dispatchers.IO) {

            try {

            val db = Room.databaseBuilder(this@MainActivity, AppDatabase::class.java, "TestDB3.db")
                .createFromAsset("myapp.db")
                .build()
//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).build()

            val questions = db.questionDao().getAll()
            Log.i("questions_tag", "onCreate: ${questions.toString()}")
            } catch (e : Exception){
                Log.i("db_error", "onCreate: ${e.message}")
            }
        }
    }
}