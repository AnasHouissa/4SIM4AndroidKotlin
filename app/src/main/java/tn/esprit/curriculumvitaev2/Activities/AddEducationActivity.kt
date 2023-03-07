package tn.esprit.curriculumvitaev2.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import tn.esprit.curriculumvitaev2.databinding.ActivityAddEducationBinding
import tn.esprit.curriculumvitaev2.databinding.ActivityAddExperienceBinding

class AddEducationActivity : AppCompatActivity() {
    lateinit var view: ActivityAddEducationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view= ActivityAddEducationBinding.inflate(layoutInflater)
        setContentView(view.root)
        setupActionBar()

    }

    fun setupActionBar(){
        supportActionBar!!.title = "Add Education"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}