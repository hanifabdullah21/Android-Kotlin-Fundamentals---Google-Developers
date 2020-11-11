package com.hanifabdullah21.dicerollerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            countUp()
        }

        //TODO Homework Anatomy of Basic Android Project
        reset.setOnClickListener {
            result_text.text = "0"
            result_text2.text = "0"
            dice_image.setImageResource(getRandomDiceImage(0))
            dice_image2.setImageResource(getRandomDiceImage(0))
        }

    }

    private fun countUp() {
        try {
            val number = result_text.text.toString().toInt()
            val number2 = result_text2.text.toString().toInt()
            if (number < 6 && number2 < 6){
                result_text.text = ""+(number+1)
                result_text2.text = ""+(number2+1)
                dice_image.setImageResource(getRandomDiceImage(number+1))
                dice_image2.setImageResource(getRandomDiceImage(number2+1))
            }
        }catch (e: NumberFormatException){
            result_text.text = "1"
            result_text2.text = "1"
            dice_image.setImageResource(getRandomDiceImage(1))
            dice_image2.setImageResource(getRandomDiceImage(1))
        }
    }

    private fun rollDice() {
        val randomInt = (1..6).random()
        val randomInt2 = (1..6).random()
        result_text.text = randomInt.toString()
        result_text2.text = randomInt2.toString()

        dice_image.setImageResource(getRandomDiceImage(randomInt))
        dice_image2.setImageResource(getRandomDiceImage(randomInt2))
    }

    //TODO Challenge Image resources and compatibility
    private fun getRandomDiceImage(randomInt: Int): Int{
        return when(randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_empty
        }
    }
}