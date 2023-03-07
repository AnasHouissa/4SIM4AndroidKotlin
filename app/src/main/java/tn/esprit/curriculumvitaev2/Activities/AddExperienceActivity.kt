package tn.esprit.curriculumvitaev2.Activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.datepicker.MaterialDatePicker
import tn.esprit.curriculumvitaev2.Database.Database
import tn.esprit.curriculumvitaev2.Database.Models.Experience
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.ActivityAddExperienceBinding
import java.text.SimpleDateFormat
import java.util.*

class AddExperienceActivity : AppCompatActivity() {
    lateinit var view: ActivityAddExperienceBinding
    lateinit var db: Database
    lateinit var dpicker: MaterialDatePicker<Long>

    var picUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityAddExperienceBinding.inflate(layoutInflater)
        db = Database.getDatabase(this)
        setContentView(view.root)


        setupActionBar()
        setupTextFields()


        view.ivAddEXP.setOnClickListener {
            imageChooser()
        }



        view.btnSaveExp.setOnClickListener {
            val compName = view.etaddCompanyName.text.toString()
            val compAdress = view.etaddCompanyAdress.text.toString()
            val startDate = view.etaddStartDate.text.toString()
            val endDate = view.etaddEndDate.text.toString()
            val experience = Experience(
                picUri!!,
                compName,
                compAdress,
                startDate,
                endDate,
                "Le lorem ipsum est, en imprimerie, une suite de mots sans signification utilisée à titre provisoire pour calibrer une mise en page, le texte définitif venant remplacer le faux-texte dès qu'il est prêt ou que la mise en page est achevée. "
            )
            db.getExpDao().upsertExperience(experience)
            finish()
        }
    }

    private fun setupTextFields() {
        view.etaddEndDate.isFocusable = false
        view.etaddEndDate.isCursorVisible = false
        view.etaddStartDate.isFocusable = false
        view.etaddStartDate.isCursorVisible = false
        view.etaddStartDate.setOnClickListener {
            setupDatepicker(view.etaddStartDate)
        }

        view.etaddEndDate.setOnClickListener {
            setupDatepicker(view.etaddEndDate)
        }
    }

    fun setupDatepicker(tv: TextView) {
        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        builder.setTitleText("Select a date")
        dpicker = builder.build()
        dpicker.show(supportFragmentManager, "datePicker")
        dpicker.addOnPositiveButtonClickListener { dateLong ->

            val date = Date(dateLong)
            val formatter = SimpleDateFormat("EEEE dd, yyyy", Locale.ENGLISH)
            val formattedDate = formatter.format(date)
            tv.text = formattedDate
        }

    }

    fun setupActionBar() {
        supportActionBar!!.title = "Add Experience"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun imageChooser() {

        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            picUri = uri
            view.ivAddEXP.setImageURI(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }
}