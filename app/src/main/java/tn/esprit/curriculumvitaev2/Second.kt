package tn.esprit.curriculumvitaev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import tn.esprit.curriculumvitaev2.databinding.ActivityMainBinding
import tn.esprit.curriculumvitaev2.databinding.ActivitySecondBinding

class Second : AppCompatActivity() {

    lateinit var mainView: ActivitySecondBinding
    var languages = arrayListOf<String>()
    var hobbies = arrayListOf<String>()
    var androidSkill = ""
    var iOSSkill = ""
    var flutterSkill = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        supportActionBar?.title = "Create your Resume"

        mainView.btnSubmit.setOnClickListener {
            submitData()
        }
    }

    fun submitData() {
        validateData()

        val fullname = intent.getStringExtra("fullname")
        val email = intent.getStringExtra("email")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")
        val userPic = intent.getStringExtra("userPic")

        val resultIntent = Intent(this, Resume::class.java)
        resultIntent.putExtra("fullname", fullname)
        resultIntent.putExtra("email", email)
        resultIntent.putExtra("age", age)
        resultIntent.putExtra("gender", gender)
        resultIntent.putExtra("languages", languages)
        resultIntent.putExtra("hobbies", hobbies)
        resultIntent.putExtra("androidSkill", androidSkill)
        resultIntent.putExtra("iOSSkill", iOSSkill)
        resultIntent.putExtra("flutterSkill", flutterSkill)
        resultIntent.putExtra("userPic", userPic)
        startActivity(resultIntent)

    }

    fun validateData() {

        languages.clear()
        if (mainView.cbAr.isChecked) languages.add("Arabic")
        if (mainView.cbAn.isChecked) languages.add("English")
        if (mainView.cbFr.isChecked) languages.add("French")

        hobbies.clear()
        if (mainView.cbMusic.isChecked) hobbies.add("Music")
        if (mainView.cbSport.isChecked) hobbies.add("Sport")
        if (mainView.cbGames.isChecked) hobbies.add("Games")

        androidSkill = mainView.sbAndroid.progress.toString().replace(".0", "")
        iOSSkill = mainView.sbiOS.progress.toString().replace(".0", "")
        flutterSkill = mainView.sbFlutter.progress.toString().replace(".0", "")


    }
}