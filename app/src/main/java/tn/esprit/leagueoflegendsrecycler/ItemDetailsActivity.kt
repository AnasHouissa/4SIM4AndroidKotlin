package tn.esprit.leagueoflegendsrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.esprit.leagueoflegendsrecycler.databinding.ActivityItemDetailsBinding

class ItemDetailsActivity : AppCompatActivity() {
    lateinit var mainView:ActivityItemDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView=ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(mainView.root)
        setupView()
    }

    private fun setupView(){
        supportActionBar!!.title=(intent.getStringExtra("name")!!.split(": ")[1])
        mainView.imageView.setImageResource(intent.getIntExtra("img",0))
        mainView.tvItemName.text=(intent.getStringExtra("name"))
        mainView.tvItemDesc.text=(intent.getStringExtra("desc"))
    }
}