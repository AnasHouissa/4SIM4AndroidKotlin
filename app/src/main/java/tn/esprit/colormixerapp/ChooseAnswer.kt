package tn.esprit.colormixerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.esprit.colormixerapp.databinding.ActivityChooseAnswerBinding

class ChooseAnswer : AppCompatActivity() {
    lateinit var mainView: ActivityChooseAnswerBinding
    private var colorsSelected = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivityChooseAnswerBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        supportActionBar?.title = "Choose your answer"

        setupViews()

        mainView.btnSubmit.setOnClickListener {
            displayResult()
        }
    }

    private fun setupViews() {
        colorsSelected = intent.getStringArrayListExtra("colors")!!
        mainView.tvYouchoose.text =
            " You chose ${colorsSelected.get(0)} and ${colorsSelected.get(1)} !"
    }

    fun displayResult() {
        val username = intent.getStringExtra("user")!!
        val responseResult = checkResponse()
        val resuIntent = Intent(this, Result::class.java)
        resuIntent.putExtra("user", username)
        resuIntent.putExtra("result", responseResult)
        startActivity(resuIntent)
    }

    fun checkResponse(): Boolean {
        if (mainView.rbPurple.isChecked && checkUsersChoice("red", "blue")) {
            return true
        }
        if (mainView.rbGreen.isChecked && checkUsersChoice("yellow", "blue")) {
            return true
        }
        if (mainView.rbOrange.isChecked && checkUsersChoice("red", "yellow")) {
            return true
        }
        return false
    }

    fun checkUsersChoice(color1: String, color2: String): Boolean {
        if (colorsSelected.contains(color1) && colorsSelected.contains(color2)) return true
        return false
    }
}