package com.example.lab8

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var answerHistoryText: String = ""
    var questionHistoryText: String = ""
    var answerText: String = ""

    lateinit var answerTextView : TextView
    lateinit var questionEditText: EditText
    lateinit var goButton: Button
    lateinit var historyTextView: TextView
    lateinit var historyTextView2: TextView
    lateinit var webButton: Button
    private val REQUEST_CODE = 1
    private var myHistory = history(arrayListOf<String>(), arrayListOf<String>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answerTextView = findViewById<TextView>(R.id.answerTextView)
        historyTextView = findViewById<TextView>(R.id.historyTextView)
        historyTextView2 = findViewById<TextView>(R.id.historyTextView2)
        webButton = findViewById<Button>(R.id.button2)
        questionEditText = findViewById(R.id.questionEditText)
        goButton = findViewById(R.id.button)

        goButton.setOnClickListener{

            val intent = Intent(this, MainActivity2::class.java)
            val question = questionEditText.text.toString()
            myHistory.questions.add(question)
            intent.putExtra("question", question)
            //startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE)
        }

        webButton.setOnClickListener{
            searchWeb()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( (requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
            val answer = data?.let{data.getStringExtra("answer")}
            answerTextView.setText("Player 2 answered: " + answer)
            answerText = "Player 2 answered: " + answer
            if (answer != null) {
                myHistory.answers.add(answer)
            }
        }

        viewHistory()
    }

    private fun viewHistory(){
        var questions = ""
        var answers = ""
        for (i in myHistory.questions.indices){
            questions = questions + "\n" + myHistory.questions[i] }
        for (i in myHistory.answers.indices){
            answers = answers + "\n" + myHistory.answers[i] }

        historyTextView.text = questions
        questionHistoryText = questions

        historyTextView2.text = answers
        answerHistoryText = answers
    }

    private fun searchWeb(){
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = let{ Uri.parse("https://www.google.com/search?q=magic+8+ball") }

        //ensure the device can handle the activity
        //if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        //}
        //else{
            //message
        //}
    }

    private fun updateUI(){
        //TextView
        answerTextView.text = answerText
        historyTextView.text = questionHistoryText
        historyTextView2.text = answerHistoryText

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("answerText", answerText)
        outState.putString("answerHistoryText", answerHistoryText)
        outState.putString("questionHistoryText", questionHistoryText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        answerText = savedInstanceState.getString("answerText", "")
        answerHistoryText = savedInstanceState.getString("answerHistoryText", "")
        questionHistoryText = savedInstanceState.getString("questionHistoryText", "")
        myHistory.answers.add(answerHistoryText)
        myHistory.questions.add(questionHistoryText)
        updateUI()
    }
}