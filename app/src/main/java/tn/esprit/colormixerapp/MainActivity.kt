package tn.esprit.colormixerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.colormixerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainView: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        supportActionBar?.title = "Color Mixer"


        mainView.btnMix.setOnClickListener {
            validateChoice()
        }
    }

    private fun validateChoice() {
        val username: String = mainView.etName.text.toString()

        val colors = arrayListOf<String>()

        if (mainView.cbRed.isChecked) {
            colors.add("red")
        }
        if (mainView.cbBlue.isChecked) {
            colors.add("blue")
        }
        if (mainView.cbYellow.isChecked) {
            colors.add("yellow")
        }
        if (colors.size != 2) {
            Toast.makeText(this, "Please select two colors", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, ChooseAnswer::class.java)
            intent.putExtra("user", username)
            intent.putExtra("colors", colors)
            startActivity(intent)

        }
    }

}