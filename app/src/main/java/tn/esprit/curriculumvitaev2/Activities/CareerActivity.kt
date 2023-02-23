package tn.esprit.curriculumvitaev2.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import tn.esprit.curriculumvitaev2.Fragments.EducationFragment
import tn.esprit.curriculumvitaev2.Fragments.ExperienceFragment
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.ActivityCareerBinding

class CareerActivity : AppCompatActivity() {
    lateinit var mainView:ActivityCareerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView=ActivityCareerBinding.inflate(layoutInflater)
        setContentView(mainView.root)


        setupActionBar()
        addFragment(ExperienceFragment())
        mainView.btnEducation.setOnClickListener {
            replaceFragment(EducationFragment())
        }
        mainView.btnExperience.setOnClickListener {
            replaceFragment(ExperienceFragment())
        }
    }


    fun addFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().add(mainView.fl.id, fragment).commit()
        return true
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(mainView.fl.id, fragment).commit()
    }
    fun setupActionBar(){
        supportActionBar!!.title = "Your Career"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

//  android.R.id.home  is a built-in resource ID in the Android platform that represents the home or up button in the action bar