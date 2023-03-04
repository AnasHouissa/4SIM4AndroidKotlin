package tn.esprit.loldatastorage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tn.esprit.leagueoflegendrecyclerview.championList.ChampionAdapter
import tn.esprit.leagueoflegendrecyclerview.data.Champion
import tn.esprit.loldatastorage.utils.AppDataBase
import tn.esprit.loldatastorage.utils.Constants
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var recylcerChampion: RecyclerView
    lateinit var recylcerChampionAdapter: ChampionAdapter
    lateinit var champList: MutableList<Champion>

    lateinit var btnAdd: FloatingActionButton

    var counter: Int = 0

    lateinit var sharedpref: SharedPreferences
    lateinit var db: AppDataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylcerChampion = findViewById(R.id.recyclerChampion)
        btnAdd = findViewById(R.id.btnAdd)

        sharedpref = getSharedPreferences(Constants.SHARED_PREF_AUTH, MODE_PRIVATE)
        db = AppDataBase.getDatabase(this)

        btnAdd.setOnClickListener {
            addToDataBase()
        }

        champList = ArrayList()
        champList.addAll(db.getChampionDao().getAllChamps())

        recylcerChampionAdapter = ChampionAdapter(db)
        recylcerChampionAdapter.differ.submitList(champList)

        recylcerChampion.apply {
            adapter = recylcerChampionAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    fun addToDataBase() {
        var champion: Champion? = null

        when (counter) {
            0 -> champion = Champion(
                1,
                champPic = R.drawable.ic_leesin,
                champName = "Lee Sin",
                champRole = "COMBATTANT: Jungler"
            )
            1 -> champion = Champion(
                2,
                champPic = R.drawable.ic_missfortune,
                champName = "Miss Fortune",
                champRole = "TIREUR: ADC"
            )
            2 -> champion = Champion(
                3,
                champPic = R.drawable.ic_thresh,
                champName = "Thresh",
                champRole = "SUPPORT"
            )
            3 -> champion = Champion(
                4,
                champPic = R.drawable.ic_nasus,
                champName = "Nasus",
                champRole = "COMBATTANT: Top"
            )
            4 -> champion = Champion(
                5,
                champPic = R.drawable.ic_ahri,
                champName = "Ahri",
                champRole = "MAGE: MID"
            )
            5 -> champion = Champion(
                6,
                champPic = R.drawable.ic_annie,
                champName = "Annie",
                champRole = "MAGE: MID"
            )
            6 -> champion = Champion(
                7,
                champPic = R.drawable.ic_ashe,
                champName = "Ashe",
                champRole = "TIREUR: ADC"
            )
            7 -> champion = Champion(
                8,
                champPic = R.drawable.ic_blitzcrank,
                champName = "Blitzcrank",
                champRole = "TANK: Support"
            )
            8 -> champion = Champion(
                9,
                champPic = R.drawable.ic_ekko,
                champName = "Ekko",
                champRole = "ASSASSIN: MID/Jungle"
            )
            9 -> champion = Champion(
                10,
                champPic = R.drawable.ic_quinn,
                champName = "Quinn",
                champRole = "TIREUR: TOP"
            )
            10 -> champion = Champion(
                11,
                champPic = R.drawable.ic_velkoz,
                champName = "Velkoz",
                champRole = "MAGE: MID/Support"
            )
        }

        counter++

        try {
            recylcerChampionAdapter.addItem(champion!!)


        } catch (ex: Exception) {
            Toast.makeText(this, "Could not add the champion !", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mLogout -> {
                sharedpref.edit().clear().apply()
                finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}