package tn.esprit.leagueoflegendsrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.leagueoflegendsrecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainView:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        var champsAdapter= ChampsAdapter()
        mainView.rv.adapter=champsAdapter
        mainView.rv.layoutManager= LinearLayoutManager(this)
        champsAdapter.differ.submitList(getChamps())
    }

    private fun getChamps():MutableList<Champ>{
        var champs = mutableListOf<Champ>()
        champs.add(Champ("Miss Fotrune",R.drawable.ic_missfortune,"ADC"))
        champs.add(Champ("Lee Sin",R.drawable.ic_leesin,"Jungler"))
        champs.add(Champ("Nasus",R.drawable.ic_nasus,"Support"))
        champs.add(Champ("Miss Fotrune",R.drawable.ic_missfortune,"ADC"))
        champs.add(Champ("Lee Sin",R.drawable.ic_leesin,"Jungler"))
        champs.add(Champ("Nasus",R.drawable.ic_nasus,"Support"))
       return champs;
    }
}