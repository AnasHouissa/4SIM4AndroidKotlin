package tn.esprit.curriculumvitaev2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.FragmentHobbiesBinding
import tn.esprit.curriculumvitaev2.databinding.FragmentLangBinding

class LangFragment : Fragment() {
    private lateinit var mainView: FragmentLangBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = FragmentLangBinding.inflate(inflater, container, false)
        setupView()
        return mainView.root
    }


    fun setupView() {

        val languages = arguments?.getStringArrayList("languages")
        mainView.cbAn.isEnabled = false
        mainView.cbAr.isEnabled = false
        mainView.cbFr.isEnabled = false


        if (languages!!.contains("Arabic")) mainView.cbAr.isChecked = true
        if (languages!!.contains("French")) mainView.cbFr.isChecked = true
        if (languages!!.contains("English")) mainView.cbAn.isChecked = true


    }
}