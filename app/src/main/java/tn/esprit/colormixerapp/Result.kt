package tn.esprit.colormixerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import tn.esprit.colormixerapp.databinding.ActivityResultBinding

class Result : AppCompatActivity() {
    lateinit var mainView: ActivityResultBinding
    lateinit var username: String
    var res: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainView = ActivityResultBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        setupViews()

        mainView.btnQuit.setOnClickListener {
            finish()
        }
    }

    private fun setupViews() {
        username = intent.getStringExtra("user")!!
        res = intent.getBooleanExtra("result", false)
        if (res) {
            mainView.tvCong.text = "Congratulations $username !"
            supportActionBar?.title = "Success"
        } else {
            supportActionBar?.title = "Failure"
            mainView.ll.setBackgroundColor(getColor(R.color.error))
            mainView.tvCong.text = "Sorry $username !"
            mainView.tvAnswerResult.text = "Your answer is wrong !"
            mainView.btnQuit.setBackgroundColor(getColor(R.color.error))
            mainView.iv.setImageResource(R.mipmap.ic_wrong)
            mainView.ivTxt.text = "WRONG"
        }

    }
}