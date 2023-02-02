package tn.esprit.dicegameseance1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tn.esprit.dicegameseance1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.btnRoll.setOnClickListener { rollDice() }
    }

    private fun rollDice(){
        val dice = Dice(6)
        val diceValue = dice.roll()
        val dicePic = when (diceValue){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        b.ivDice.setImageResource(dicePic)

    }
}