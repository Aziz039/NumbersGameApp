package com.example.numbersgameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

lateinit var tv_answer: TextView
lateinit var tv_chances: TextView
lateinit var ti_userAnswer: TextInputEditText
lateinit var bt_guess: Button
var chances: Int = 0
var randomNumber: Int = 0
var gameResult: String = ""


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_answer = findViewById(R.id.tv_answer)
        tv_chances = findViewById(R.id.tv_chances)
        ti_userAnswer = findViewById(R.id.ti_userAnswer)
        bt_guess = findViewById(R.id.bt_guess)
        chances = 3
        randomNumber = Random.nextInt(11)
        gameResult = ""
        bt_guess.setOnClickListener { game() }
    }

    fun game() {
        if (chances > 1) {
            if (ti_userAnswer.text.isNullOrEmpty()) {2
                Log.d("MainActivityGame", "Enter a number..")
            } else {
                tv_answer.text = "You guessed ${ti_userAnswer.text}"
                if (ti_userAnswer.text.toString().toInt() == randomNumber) {
                    gameResult = "You won!"
                    customAlert()
                } else {
                    chances--
                    if (chances == 1) {
                        tv_chances.text = "You have $chances guess left!"
                        Log.d("MainActivityGame", "answer is $randomNumber")
                    } else {
                        tv_chances.text = "You have $chances guesses left!"
                    }
                }
            }

        } else {
            tv_chances.text = "You have $chances guess left!"
            gameResult = "You lost the game!"
            customAlert()
        }
    }

    private fun customAlert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)

        // here we set the message of our alert dialog
        dialogBuilder.setMessage("Do you want to play again?:")
            // positive button text and action
            .setPositiveButton("yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> finish()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("$gameResult")

        // show alert dialog
        alert.show()
    }
}