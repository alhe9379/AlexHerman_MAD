package com.example.lab7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    var message: String = ""
    lateinit var messageView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageView = findViewById<TextView>(R.id.messageTextView)
    }

    fun buttonClick(view: android.view.View){
        doCalculation()
    }

    fun doCalculation(){
        var layoutRoot = findViewById<ConstraintLayout>(R.id.root_layout)

        val switch = findViewById<SwitchMaterial>(R.id.switch1)
        val lotteryCheckBox = findViewById<CheckBox>(R.id.checkBox)
        val scratchOffCheckBox = findViewById<CheckBox>(R.id.checkBox2)
        var spinner = findViewById<Spinner>(R.id.spinner)

        if(switch.isChecked){
            var buyLottery = lotteryCheckBox.isChecked
            var buyScratchOff = scratchOffCheckBox.isChecked
            var ticketTypeMultiplier: Int
            if(buyLottery && buyScratchOff){ ticketTypeMultiplier = 2 }
            else if(!buyLottery && !buyScratchOff){ ticketTypeMultiplier = 0 }
            else { ticketTypeMultiplier = 1 }


            var price = -1
            var radioButtonIsChecked = findViewById<RadioButton>(R.id.radioButton).isChecked
            var radioButton2IsChecked = findViewById<RadioButton>(R.id.radioButton2).isChecked
            if(radioButtonIsChecked){ price = 20 }
            if(radioButton2IsChecked){ price = 50 }

            var spinnerMultiplier = spinner.selectedItem.toString().toInt()

            var computedPrice = ticketTypeMultiplier * price * spinnerMultiplier

            messageView.text = "You spent $$computedPrice on tickets!!! You will likely lose all of that money!"
        }

        else{
            val gamblinSnackbar = Snackbar.make(layoutRoot, "You can't buy tickets if you're not a gamblin man.", Snackbar.LENGTH_SHORT)
            gamblinSnackbar.show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("message", message)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        message = savedInstanceState.getString("message", "")
        doCalculation()
    }
}
