package tn.esprit.curriculumvitaev2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.curriculumvitaev2.Adapters.ExperienceAdapter
import tn.esprit.curriculumvitaev2.Database.Database
import tn.esprit.curriculumvitaev2.Database.Models.Experience
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.FragmentExperienceBinding
import tn.esprit.curriculumvitaev2.databinding.FragmentHobbiesBinding


class ExperienceFragment : Fragment() {
    private lateinit var mainView: FragmentExperienceBinding
    private lateinit var db: Database

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = FragmentExperienceBinding.inflate(inflater, container, false)
        db = Database.getDatabase(requireContext())
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
        return db.getExpDao().getAllExperiences()

    }

}