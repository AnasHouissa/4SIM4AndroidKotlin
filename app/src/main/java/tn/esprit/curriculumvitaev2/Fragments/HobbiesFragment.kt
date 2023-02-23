package tn.esprit.curriculumvitaev2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.FragmentHobbiesBinding
import tn.esprit.curriculumvitaev2.databinding.FragmentSkillsBinding

class HobbiesFragment : Fragment() {
    private lateinit var mainView: FragmentHobbiesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = FragmentHobbiesBinding.inflate(inflater, container, false)
        setupView()
        return mainView.root
    }


    fun setupView() {

        val hobbies = arguments?.getStringArrayList("hobbies")
        mainView.cbMusic.isEnabled = false
        mainView.cbGames.isEnabled = false
        mainView.cbSport.isEnabled = false

        if (hobbies!!.contains("Music")) mainView.cbMusic.isChecked = true
        if (hobbies!!.contains("Games")) mainView.cbGames.isChecked = true
        if (hobbies!!.contains("Sport")) mainView.cbSport.isChecked = true


    }

}