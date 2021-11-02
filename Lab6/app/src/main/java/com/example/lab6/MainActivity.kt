package com.example.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View) {
        val message = findViewById<TextView>(R.id.message)
        val nameInput = findViewById<EditText>(R.id.editText)
        val name = nameInput.text
        message.text = "Hi $name. We've been trying to reach you about your cars extended warranty."

        val imageObject = findViewById<ImageView>(R.id.imageView)
        imageObject.setImageResource(R.drawable.car)
    }

    fun hangup(view: View) {
        val message = findViewById<TextView>(R.id.message)
        message.text = ""

        val imageObject = findViewById<ImageView>(R.id.imageView)
        imageObject.setImageResource(R.drawable.phonering)
    }
}