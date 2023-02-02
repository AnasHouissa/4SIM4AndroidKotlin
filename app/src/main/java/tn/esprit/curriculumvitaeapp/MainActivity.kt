package tn.esprit.curriculumvitaeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import tn.esprit.curriculumvitaeapp.Entities.Skill
import tn.esprit.curriculumvitaeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainLayout: ActivityMainBinding

    // lateinit var skills_tmp:  MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainLayout = ActivityMainBinding.inflate(layoutInflater);
        //   skills_tmp= mutableListOf()


        setContentView(mainLayout.root)
        mainLayout.btnNext.setOnClickListener {
            nextClicked()
        }
        mainLayout.btnReset.setOnClickListener {
            reset()
        }
    }

    private fun reset() {
        mainLayout.etAge.text.clear()
        mainLayout.etEmail.text.clear()
        mainLayout.etFullname.text.clear()
        mainLayout.rbMale.isChecked = true
        mainLayout.sbAndroid.progress = 0
        mainLayout.sbIos.progress = 0
        mainLayout.sbFlutter.progress = 0
        // skills_tmp.clear()
    }

    private fun nextClicked() {

        val android = Skill("Android", mainLayout.sbAndroid.progress)
        val ios = Skill("iOS", mainLayout.sbIos.progress)
        val flutter = Skill("Flutter", mainLayout.sbFlutter.progress)
        val skills = listOf(android, ios, flutter)
        //  skills_tmp.clear()


        if (mainLayout.etFullname.text.isEmpty() || mainLayout.etAge.text.isEmpty() || mainLayout.etEmail.text.isEmpty()) {
            displayToast(R.string.toast_empty, "")
        } else if (!checkRegex(
                mainLayout.etEmail.text.toString(),
                Patterns.EMAIL_ADDRESS.toRegex()
            )
        ) {
            displayToast(R.string.toast_email, "")
        } else {
            when {
                /****/
                skills.maxByOrNull { it.value }!!.value > 80 -> {
                    skills.forEach { skill ->


                        if (skill.value > 80 && skill == skills.maxByOrNull { it.value }
                        ) {
                            //  skills_tmp.add(skill.name)
                            displayToast(R.string.toast_excellent, skill.name)

                        }

                    }
                    //if(skills_tmp.size>1){displayToast(R.string.toast_excellent_skills, "")}else{displayToast(R.string.toast_excellent, skills_tmp[0])}
                }
                /****/
                skills.maxByOrNull { it.value }!!.value <= 30 -> displayToast(
                    R.string.toast_below30,
                    ""
                )
                else -> displayToast(R.string.toast_notbad, "")

            }


        }
    }

    private fun checkRegex(value: String, regex: Regex): Boolean {
        return value.matches(regex)
    }

    private fun displayToast(stringID: Int, concatString: String) {
        if (concatString.isEmpty()) {
            Toast.makeText(this, getString(stringID), Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, getString(stringID, concatString), Toast.LENGTH_SHORT).show()

        }
    }
}