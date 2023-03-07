package tn.esprit.curriculumvitaev2.Activities

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import tn.esprit.curriculumvitaev2.Fragments.AboutMeFragment
import tn.esprit.curriculumvitaev2.Fragments.HobbiesFragment
import tn.esprit.curriculumvitaev2.Fragments.LangFragment
import tn.esprit.curriculumvitaev2.Fragments.SkillsFragment
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.Utils.Constants
import tn.esprit.curriculumvitaev2.databinding.ActivityResumeBinding

class Resume : AppCompatActivity() {
    lateinit var mainView: ActivityResumeBinding
    lateinit var skillsFragment: SkillsFragment
    lateinit var hobbiesFragment: HobbiesFragment
    lateinit var langFragment: LangFragment
    lateinit var aboutmeFragment: AboutMeFragment
    lateinit var pref: SharedPreferences

    lateinit var fullName: String
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        pref=getSharedPreferences(Constants.SHARED_PREF_Data, MODE_PRIVATE)


        setupActionBar()
        setupView()
        langData()
        hobbiesData()
        skillsData()
        aboutmeData()
        addFragment(skillsFragment)

        mainView.btnSkills.setOnClickListener { replaceFragment(skillsFragment) }
        mainView.btnHobbies.setOnClickListener { replaceFragment(hobbiesFragment) }
        mainView.btnLang.setOnClickListener { replaceFragment(langFragment) }
        mainView.btnCareer.setOnClickListener { startActivity(Intent(this,CareerActivity::class.java)) }
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
            R.id.logout -> {
                displayLogoutDialog()
                true
            }
            else -> return super.onOptionsItemSelected(item)

        }
    }
    fun displayLogoutDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure you want to logout the activity?")
        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            pref.edit().clear().apply()
            finish()
        }
        builder.setNegativeButton("No") { _: DialogInterface, _: Int ->

        }

        builder.show()
    }
    fun setupActionBar(){
        supportActionBar!!.title = "Your Resume"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_home)
    }

    fun setupView() {
        fullName =pref.getString("fullname","-")!!
        email =pref.getString("email","-")!!
        mainView.iv.setImageURI(Uri.parse(pref.getString("userPic","")))
        mainView.tvName.text =fullName
        mainView.tvEmail.text =email
    }

    fun aboutmeData() {
        aboutmeFragment = AboutMeFragment()
        val age = pref.getString("age","-")
        val gender = pref.getString("gender","-")
        val aboutmeBundle = Bundle()
        aboutmeBundle.putString("age", age)
        aboutmeBundle.putString("gender", gender)
        aboutmeBundle.putString("name", fullName)
        aboutmeBundle.putString("email", email)
        aboutmeFragment.arguments = aboutmeBundle
    }

    fun langData() {
        langFragment = LangFragment()
        val languages = pref.getStringSet("languages", mutableSetOf())
        val langBundle = Bundle()
        langBundle.putStringArrayList("languages", ArrayList(languages))
        langFragment.arguments = langBundle
    }

    fun hobbiesData() {
        hobbiesFragment = HobbiesFragment()
        val hobbies = pref.getStringSet("hobbies",mutableSetOf())
        val hobbiesBundle = Bundle()
        hobbiesBundle.putStringArrayList("hobbies", ArrayList(hobbies))
        hobbiesFragment.arguments = hobbiesBundle

    }

    fun skillsData() {
        skillsFragment = SkillsFragment()

        val androidSkill = pref.getString("androidSkill","0")
        val iOSSkill = pref.getString("iOSSkill","0")
        val flutterSkill = pref.getString("flutterSkill","0")

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