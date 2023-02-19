package tn.esprit.curriculumvitaev2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.curriculumvitaev2.databinding.ActivityMainBinding
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var mainView: ActivityMainBinding
    var firstDrawble: Drawable? = null
    var picUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        mainView.btnNext.setOnClickListener {
            validateIdentity()
        }

        mainView.iv.setOnClickListener {
            imageChooser()
        }
    }

    fun init() {
        supportActionBar?.title = "Create your Resume"
        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)
        firstDrawble = ContextCompat.getDrawable(this, R.drawable.ic_person)

    }

    fun validateIdentity() {
        if (fieldsValidation()) {

            val fullname = mainView.etFirstName.text.toString()
            val email = mainView.etEmail.text.toString()
            val age = mainView.etAge.text.toString()
            val userPic = picUri
            val gender = findViewById<RadioButton>(mainView.rgGender.checkedRadioButtonId).text

            val intent = Intent(this, Second::class.java)
            intent.putExtra("fullname", fullname)
            intent.putExtra("email", email)
            intent.putExtra("age", age)
            intent.putExtra("gender", gender)
            intent.putExtra("userPic", userPic.toString())
            startActivity(intent)
        }


    }

    private fun fieldsValidation(): Boolean {
        val validateFullName = validateFullName()
        val validateEmail = validateEmail()
        val validateAge = validateAge()
        val validatePicture = validatePic()
        return validateFullName && validateEmail && validateAge && validatePicture
    }

    fun displayError(layout: TextInputLayout, msg: String) {
        layout.isErrorEnabled = true
        layout.error = msg
    }

    fun validatePic(): Boolean {
        if (mainView.iv.drawable?.constantState == firstDrawble?.constantState) {
            Toast.makeText(this, "Select an image!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun validateFullName(): Boolean {
        var isvalid: Boolean

        if (mainView.etFirstName.text.toString().isEmpty()) {
            displayError(mainView.tlFirstName, "Full Name cannot be empty!")

            isvalid = false
        } else {
            mainView.tlFirstName.isErrorEnabled = false
            isvalid = true
        }
        return isvalid
    }

    fun validateEmail(): Boolean {
        var isvalid = false
        if (mainView.etEmail.text.toString().isEmpty()) {
            displayError(mainView.tlEmail, "Email cannot be empty!")
            isvalid = false
        } else {

            if (!mainView.etEmail.text.toString().matches(Patterns.EMAIL_ADDRESS.toRegex())) {
                mainView.tlEmail.isErrorEnabled = true
                mainView.tlEmail.error = "Enter a valid email address!"
                displayError(mainView.tlEmail, "Enter a valid email address!")
                isvalid = false
            } else {
                mainView.tlEmail.isErrorEnabled = false
                isvalid = true
            }
        }
        return isvalid
    }

    fun validateAge(): Boolean {
        var isvalid = false
        if (mainView.etAge.text.toString().isEmpty()) {
            displayError(mainView.tlAge, "Age cannot be empty!")
            isvalid = false
        } else {
            mainView.tlAge.isErrorEnabled = false
            isvalid = true
        }
        return isvalid
    }


    fun imageChooser() {

        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            picUri=uri
            mainView.iv.setImageURI(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

}