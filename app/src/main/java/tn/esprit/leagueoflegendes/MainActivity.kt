package tn.esprit.leagueoflegendes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import tn.esprit.leagueoflegendes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)
        setSupportActionBar(mainView.toolbar)
        addFragment(ChampsFragment())
        mainView.btnChamps.setOnClickListener { replaceFragment(ChampsFragment()) }
        mainView.btnSpells.setOnClickListener { replaceFragment(SpellsFragment()) }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,mainView.toolbar.menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.info -> {
                replaceFragment(DetailsFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    fun addFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().add(mainView.fl.id, fragment).commit()
        return true
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(mainView.fl.id, fragment).commit()
    }



}