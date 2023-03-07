package tn.esprit.curriculumvitaev2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.curriculumvitaev2.Activities.MainActivity
import tn.esprit.curriculumvitaev2.Activities.Resume
import tn.esprit.curriculumvitaev2.Utils.Constants

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val pref = getSharedPreferences(Constants.SHARED_PREF_Data, MODE_PRIVATE)
        if (pref.contains("fullname")) startActivity(Intent(this, Resume::class.java))
        else startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}