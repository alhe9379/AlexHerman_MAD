package com.example.lab8

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {

    var questionText: String = ""
    lateinit var answerEditText: EditText
    lateinit var questionTextView: TextView
    private var question: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        questionTextView = findViewById<TextView>(R.id.historyTextView)
        answerEditText = findViewById(R.id.answerEditText)

        question = intent.getStringExtra("question")

        question?.let { Log.i("question reciecved", it)};

        question?.let{questionTextView.text = "Player 1 asked: $question"}
        question?.let{questionText = "Player 1 asked: $question"}
    }

    override fun onBackPressed(){
        val data = Intent()
        data.putExtra("answer", answerEditText.text.toString())
        setResult(Activity.RESULT_OK, data)
        super.onBackPressed()
        finish()
    }

    private fun updateUI(){
        //TextView
        questionTextView.text = questionText
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("questionText", questionText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        questionText = savedInstanceState.getString("questionText", "")
        updateUI()
    }
}