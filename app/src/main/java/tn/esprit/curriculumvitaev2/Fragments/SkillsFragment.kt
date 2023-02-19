package tn.esprit.curriculumvitaev2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.FragmentSkillsBinding


class SkillsFragment : Fragment() {

    private lateinit var mainView: FragmentSkillsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = FragmentSkillsBinding.inflate(inflater, container, false)
        setupView()
        return mainView.root
    }


    fun setupView() {

        val androidSkill = arguments?.getString("androidSkill")?.toInt()
        val iOSSkill = arguments?.getString("iOSSkill")?.toInt()
        val flutterSkill = arguments?.getString("flutterSkill")?.toInt()

        mainView.sbAndroid.progress = androidSkill!!
        mainView.sbAndroid.isEnabled = false

        mainView.sbiOS.progress = iOSSkill!!
        mainView.sbiOS.isEnabled = false

        mainView.sbFlutter.progress = flutterSkill!!
        mainView.sbFlutter.isEnabled = false


    }

}