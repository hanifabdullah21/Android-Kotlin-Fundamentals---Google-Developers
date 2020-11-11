package com.hanifabdullah21.dicerollerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roll_button.setOnClickListener {
            rollDice()
        }

        //TODO Challenge Anatomy of Basic Android Project
        count_up.setOnClickListener {
            try {
                val number = count_up.text.toString().toInt()
                if (number < 6){
                    result_text.text = ""+(number+1)
                }
            }catch (e: NumberFormatException){
                result_text.text = "1"
            }
        }

        reset.setOnClickListener {
            result_text.text = "0"
        }

    }

    private fun rollDice() {
        val randomInt = (1..6).random()
        result_text.text = randomInt.toString()
        Toast.makeText(this, "Roll Dice", Toast.LENGTH_SHORT).show()
    }
}