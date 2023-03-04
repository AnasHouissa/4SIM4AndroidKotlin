package tn.esprit.loldatastorage

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.loldatastorage.utils.Constants.SHARED_PREF_AUTH

const val PREF_NAME = "LOGIN_PREF_LOL"
const val LOGIN = "LOGIN"
const val PASSWORD = "PASSWORD"
const val IS_REMEMBRED = "IS_REMEMBRED"

class LoginActivity : AppCompatActivity() {

    lateinit var txtLogin: TextInputEditText
    lateinit var txtLayoutLogin: TextInputLayout

    lateinit var txtPassword: TextInputEditText
    lateinit var txtLayoutPassword: TextInputLayout

    lateinit var cbRememberMe: CheckBox
    lateinit var btnLogin: MaterialButton

    lateinit var sharedpref: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtLogin = findViewById(R.id.txtLogin)
        txtLayoutLogin = findViewById(R.id.txtLayoutLogin)

        txtPassword = findViewById(R.id.txtPassword)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)

        cbRememberMe = findViewById(R.id.cbRememberMe)
        btnLogin = findViewById(R.id.btnLogin)

        sharedpref = getSharedPreferences(SHARED_PREF_AUTH, MODE_PRIVATE)


        if(sharedpref.contains("rememberMe")){
            navigate()
        }


        btnLogin.setOnClickListener{
            doLogin()
        }

    }

    private fun doLogin(){
        if (validate()){
            if (cbRememberMe.isChecked){
                val editor =sharedpref.edit()
                editor.putBoolean("rememberMe",cbRememberMe.isChecked)
                editor.putString("username",txtLogin.text.toString())
                editor.apply()

            }

            navigate()
        }
    }

    private fun validate(): Boolean {
        txtLayoutLogin.error = null
        txtLayoutPassword.error = null

        if (txtLogin.text!!.isEmpty()){
            txtLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if (txtPassword.text!!.isEmpty()){
            txtLayoutPassword.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }

    private fun navigate(){
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }
}