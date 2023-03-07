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
import tn.esprit.curriculumvitaev2.Database.Models.Education
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.databinding.FragmentEducationBinding


class EducationFragment : Fragment() {
    private lateinit var mainView: FragmentEducationBinding
    private val educAdapter = EducationAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = FragmentEducationBinding.inflate(layoutInflater, container, false)
        setupRecycler()
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

    fun getDataFromApi(): MutableList<Education> {
        return mutableListOf<Education>(
         /*   Education(
                R.drawable.ic_logo_massachusetts,
                "Massachusetts",
                "United States",
                "01/09/2019",
                "TODAY"
            ),
            Education(
                R.drawable.ic_logo_oxford,
                "Oxford",
                "United Kingdom",
                "01/09/2018",
                "01/03/2019",
            ),
            Education(
                R.drawable.ic_logo_stanford,
                "Stanford",
                "United Kingdom",
                "01/09/2016",
                "01/09/2017",
            ),
            Education(
                R.drawable.ic_logo_cambridge,
                "Cambridge",
                "United Kingdom",
                "01/09/2016",
                "01/09/2017",
            ),
            Education(
                R.drawable.ic_logo_harvard,
                "Harvard",
                "United Kingdom",
                "01/09/2016",
                "01/09/2017",
            ),
            Education(
                R.drawable.ic_logo_esprit,
                "Esprit",
                "Tunisia",
                "01/09/2013",
                "01/09/2015",
            ),
            Education(
                R.drawable.ic_logo_cambridge,
                "Cambridge",
                "United Kingdom",
                "01/09/2016",
                "01/09/2017",
            ),
            Education(
                R.drawable.ic_logo_harvard,
                "Harvard",
                "United Kingdom",
                "01/09/2016",
                "01/09/2017",
            ),
            Education(
                R.drawable.ic_logo_esprit,
                "Esprit",
                "Tunisia",
                "01/09/2013",
                "01/09/2015",
            ),*/
        )
    }
}

