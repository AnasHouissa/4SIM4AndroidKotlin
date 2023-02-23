package tn.esprit.curriculumvitaev2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.FragmentAboutMeBinding
import tn.esprit.curriculumvitaev2.databinding.FragmentHobbiesBinding

class AboutMeFragment : Fragment() {
    private lateinit var mainView: FragmentAboutMeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = FragmentAboutMeBinding.inflate(inflater, container, false)
        setupView()
        return mainView.root
    }


    fun setupView() {

        val fullname = arguments?.getString("name")
        val gender = arguments?.getString("gender")
        val age = arguments?.getString("age")
        val email = arguments?.getString("email")

        mainView.tvAboutMe.text = "Hello my name is $fullname\nI'am $age years old\nand i'm a $gender\nYou can contact me via my email : \n$email"



    }
}

