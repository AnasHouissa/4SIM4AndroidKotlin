package tn.esprit.curriculumvitaev2.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
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
    private var TAG="ExperienceFragment"
    private lateinit var mainView: FragmentExperienceBinding
    private lateinit var db: Database
    private lateinit var experienceAdapter:ExperienceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateView")
        mainView = FragmentExperienceBinding.inflate(inflater, container, false)
        db = Database.getDatabase(requireContext())
        experienceAdapter=ExperienceAdapter(db)


        return mainView.root
    }

    fun setupRecycler() {
        mainView.rc.adapter = experienceAdapter
        mainView.rc.layoutManager = LinearLayoutManager(this.context)
        experienceAdapter.diff.submitList(getDataFromApi())
    }

    fun getDataFromApi(): List<Experience> {
        return db.getExpDao().getAllExperiences()
    }

    override fun onResume() {
        super.onResume()
        setupRecycler()
    }








}