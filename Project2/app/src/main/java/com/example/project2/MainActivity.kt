package com.example.project2

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageView
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar


//https://developer.android.com/guide/topics/graphics/drawables
class MainActivity : AppCompatActivity() {
    lateinit var myDrawing: MyDrawable
    lateinit var image: ImageView
    lateinit var button: Button
    lateinit var timerTextView: TextView
    lateinit var layoutRoot : ConstraintLayout
    lateinit var scoreTextView: TextView
    var timer: CountDownTimer? = null


    //https://stackoverflow.com/questions/29398929/how-get-height-of-the-status-bar-and-soft-key-buttons-bar/29400243
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // Hide the status bar
//        //https://developer.android.com/training/system-ui/status
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        // Remember that you should never show the action bar if the
//        // status bar is hidden, so hide that too if necessary.
//        actionBar?.hide()

        myDrawing = MyDrawable()
        image = findViewById(R.id.imageView)
        layoutRoot = findViewById<ConstraintLayout>(R.id.root_layout)
        button = findViewById(R.id.button)
        timerTextView = findViewById(R.id.timerTextView)
        scoreTextView = findViewById(R.id.scoreTextView)

        button.setOnClickListener(){
            start(1)
        }
    }

    //https://stackoverflow.com/questions/10032003/how-to-make-a-countdown-timer-in-android
    fun startTimer() {
        timer = object : CountDownTimer(11000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.setText(""+millisUntilFinished / 1000)
            }
            override fun onFinish() {
                timerTextView.setText("DONE")
                lose()
            }
        }
        (timer as CountDownTimer).start()
    }

    fun cancelTimer() {
        timer?.cancel()
    }

    fun start(difficulty: Int){
        cancelTimer()
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

        scoreTextView.text = "Score: " + (myDrawing.difficulty).toString()

        button.visibility = View.GONE;
        myDrawing.difficulty = difficulty
        image.setImageDrawable(null)
        image.setImageDrawable(myDrawing)

        //https://developer.android.com/reference/android/os/CountDownTimer
        startTimer()
    }

    fun lose(){
        cancelTimer()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        //easy way to not allow user to keep playing after they lose
        myDrawing.pentagonLocationX = -1000F
        myDrawing.pentagonLocationY = -1000F
        val loseSnackbar = Snackbar.make(layoutRoot, "You lost :(", 2500)
        loseSnackbar.show()
        button.visibility = View.VISIBLE;
    }

    //https://stackoverflow.com/questions/3476779/how-to-get-the-touch-position-in-android
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.i("Touched X", x.toString())
                Log.i("Touched Y", y.toString())
                Log.i("Pentagon X", myDrawing.pentagonLocationX.toString())
                Log.i("Pentagon Y", myDrawing.pentagonLocationY.toString())

                if(y > myDrawing.pentagonLocationY + myDrawing.pentagonSize * 1.5 || y < myDrawing.pentagonLocationY){
                    Log.i("MISCLICKED Y", "Sad :(")
                    lose()
                }

                else if(x > myDrawing.pentagonLocationX + myDrawing.pentagonSize * 1.25 || x < myDrawing.pentagonLocationX - myDrawing.pentagonSize * 0.35){
                    Log.i("MISCLICKED X", "Sad :(")
                    lose()
                }

                else{
                    start(myDrawing.difficulty + 1)
                }
            }
        }

        return false
    }

}