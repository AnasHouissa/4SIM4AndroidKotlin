package tn.esprit.curriculumvitaev2.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import kotlinx.coroutines.*
import tn.esprit.curriculumvitaev2.Adapters.EducationAdapter
import tn.esprit.curriculumvitaev2.Adapters.ExperienceAdapter
import tn.esprit.curriculumvitaev2.Database.Database
import tn.esprit.curriculumvitaev2.Database.Models.Education
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.FragmentEducationBinding


class EducationFragment : Fragment() {
    private lateinit var mainView: FragmentEducationBinding
    private lateinit var db: Database

    private lateinit var educAdapter : EducationAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = FragmentEducationBinding.inflate(layoutInflater, container, false)
        db = Database.getDatabase(requireContext())
        educAdapter= EducationAdapter(db)
        swipeLayoutSetup()



        return mainView.root
    }

    fun swipeLayoutSetup() {
        mainView.swipeRefreshLayout.setColorSchemeColors(
            resources.getColor(
                R.color.colorPrimary,
                null
            )
        )
        mainView.swipeRefreshLayout.setOnRefreshListener {
            GlobalScope.launch {
                delay(2000)
                withContext(Dispatchers.Main) {
                    suspend { educAdapter.diff.currentList.clear() }
                    mainView.rc.visibility = View.GONE
                    mainView.noDataView.root.visibility = View.VISIBLE
                    mainView.swipeRefreshLayout.isRefreshing = false;
                }
            }

        }
    }

    fun setupRecycler() {
        mainView.rc.adapter = educAdapter
        mainView.rc.layoutManager = LinearLayoutManager(this.context)
        educAdapter.diff.submitList(getDataFromApi())
    }

    fun getDataFromApi(): List<Education> {
        return db.getEduDao().getAllEducations()

    }
    override fun onResume() {
        super.onResume()
        setupRecycler()
    }
}

