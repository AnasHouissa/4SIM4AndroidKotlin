package tn.esprit.curriculumvitaev2.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exp_edu_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //  android.R.id.home  is a built-in resource ID in the Android platform that represents the home or up button in the action bar
            android.R.id.home -> finish()
            R.id.addExp -> startActivity(Intent(applicationContext,AddExperienceActivity::class.java))
            R.id.addEdu -> startActivity(Intent(applicationContext,AddEducationActivity::class.java))

        }
        return super.onOptionsItemSelected(item)
    }
}

