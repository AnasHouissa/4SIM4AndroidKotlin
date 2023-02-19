package tn.esprit.curriculumvitaev2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import tn.esprit.curriculumvitaev2.Fragments.AboutMeFragment
import tn.esprit.curriculumvitaev2.Fragments.HobbiesFragment
import tn.esprit.curriculumvitaev2.Fragments.LangFragment
import tn.esprit.curriculumvitaev2.Fragments.SkillsFragment
import tn.esprit.curriculumvitaev2.databinding.ActivityResumeBinding

class Resume : AppCompatActivity() {
    lateinit var mainView: ActivityResumeBinding
    lateinit var skillsFragment: SkillsFragment
    lateinit var hobbiesFragment: HobbiesFragment
    lateinit var langFragment: LangFragment
    lateinit var aboutmeFragment: AboutMeFragment

    lateinit var fullName: String
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(mainView.root)
        supportActionBar?.title = "Curriculum Vitae"


        setupView()
        langData()
        hobbiesData()
        skillsData()
        aboutmeData()
        addFragment(skillsFragment)

        mainView.btnSkills.setOnClickListener { replaceFragment(skillsFragment) }
        mainView.btnHobbies.setOnClickListener { replaceFragment(hobbiesFragment) }
        mainView.btnLang.setOnClickListener { replaceFragment(langFragment) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.aboutMe -> {
                replaceFragment(aboutmeFragment)
                true
            }
            else -> return super.onOptionsItemSelected(item)

        }
    }

    fun setupView() {
        fullName = intent.getStringExtra("fullname")!!
        email = intent.getStringExtra("email")!!
        val userPic = intent.getStringExtra("userPic")
        mainView.iv.setImageURI(Uri.parse(userPic))
        mainView.tvName.text = fullName
        mainView.tvEmail.text = email
    }

    fun aboutmeData() {
        aboutmeFragment = AboutMeFragment()
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")
        val aboutmeBundle = Bundle()
        aboutmeBundle.putString("age", age)
        aboutmeBundle.putString("gender", gender)
        aboutmeBundle.putString("name", fullName)
        aboutmeBundle.putString("email", email)
        aboutmeFragment.arguments = aboutmeBundle
    }

    fun langData() {
        langFragment = LangFragment()
        val languages = intent.getStringArrayListExtra("languages")
        val langBundle = Bundle()
        langBundle.putStringArrayList("languages", languages)
        langFragment.arguments = langBundle
    }

    fun hobbiesData() {
        hobbiesFragment = HobbiesFragment()
        val hobbies = intent.getStringArrayListExtra("hobbies")
        val hobbiesBundle = Bundle()
        hobbiesBundle.putStringArrayList("hobbies", hobbies)
        hobbiesFragment.arguments = hobbiesBundle

    }

    fun skillsData() {
        skillsFragment = SkillsFragment()

        val androidSkill = intent.getStringExtra("androidSkill")
        val iOSSkill = intent.getStringExtra("iOSSkill")
        val flutterSkill = intent.getStringExtra("flutterSkill")

        val skillsBundle = Bundle()
        skillsBundle.putString("androidSkill", androidSkill)
        skillsBundle.putString("iOSSkill", iOSSkill)
        skillsBundle.putString("flutterSkill", flutterSkill)
        skillsFragment.arguments = skillsBundle

    }

    fun addFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().add(mainView.fl.id, fragment).commit()
        return true
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(mainView.fl.id, fragment).commit()
    }
}