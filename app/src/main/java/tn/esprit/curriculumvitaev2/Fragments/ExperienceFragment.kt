package tn.esprit.curriculumvitaev2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.curriculumvitaev2.Adapters.ExperienceAdapter
import tn.esprit.curriculumvitaev2.Models.Experience
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.FragmentExperienceBinding
import tn.esprit.curriculumvitaev2.databinding.FragmentHobbiesBinding


class ExperienceFragment : Fragment() {
    private lateinit var mainView: FragmentExperienceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = FragmentExperienceBinding.inflate(inflater, container, false)
        setupRecycler()

        return mainView.root
    }

    fun setupRecycler() {
        val experienceAdapter = ExperienceAdapter()
        mainView.rc.adapter = experienceAdapter
        mainView.rc.layoutManager = LinearLayoutManager(this.context)
        experienceAdapter.diff.submitList(getDataFromApi())
    }

    fun getDataFromApi(): List<Experience> {
        return listOf<Experience>(
            Experience(
                R.drawable.ic_logo_amazon,
                "Amazon",
                "United States",
                "01/09/2019",
                "TODAY",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            ),
            Experience(
                R.drawable.ic_logo_facebook,
                "Facebook",
                "France",
                "01/09/2018",
                "01/03/2019",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            ),
            Experience(
                R.drawable.ic_logo_microsoft,
                "Microsoft",
                "United Kingdom",
                "01/09/2016",
                "01/09/2017",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            ),
            Experience(
                R.drawable.ic_logo_esprit,
                "Esprit",
                "Tunisia",
                "01/09/2013",
                "01/09/2015",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            ),
        )
    }

}