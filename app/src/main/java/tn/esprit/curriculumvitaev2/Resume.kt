package tn.esprit.curriculumvitaev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tn.esprit.curriculumvitaev2.databinding.ActivityResumeBinding

class Resume : AppCompatActivity() {
    lateinit var mainView: ActivityResumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        supportActionBar?.title = "Your Resume"

        setupView()
    }

    fun setupView() {
        val fullname = intent.getStringExtra("fullname")
        val email = intent.getStringExtra("email")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")
        val androidSkill = intent.getStringExtra("androidSkill")
        val iOSSkill = intent.getStringExtra("iOSSkill")
        val flutterSkill = intent.getStringExtra("flutterSkill")
        val languages = intent.getStringArrayListExtra("languages")
        val hobbies = intent.getStringArrayListExtra("hobbies")


        mainView.tvName.text = "Name: $fullname"
        mainView.tvEmail.text = "Email: $email"
        mainView.tvAge.text = "Age: $age"
        mainView.tvGender.text = "Gender: $gender"
        mainView.tvAndroid.text = "Android Skills: $androidSkill"
        mainView.tviOS.text = "iOs Skills: $iOSSkill"
        mainView.tvFlutter.text = "Flutter Skills: $flutterSkill"

        mainView.tvLanguages.text = "Languages: "

        if (languages != null) {
            for (lang in languages) {
                mainView.tvLanguages.append("$lang ")
            }
        }
        mainView.tvHobbies.text = "Hobbies: "

        if (hobbies != null) {
            for (hob in hobbies) {
                mainView.tvHobbies.append("$hob ")
            }
        }
    }
}