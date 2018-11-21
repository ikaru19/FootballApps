package com.example.ikaru.footballapps.Match

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.ikaru.footballapps.Api.ApiRepository
import com.example.ikaru.footballapps.Model.League
import com.example.ikaru.footballapps.Model.Match
import com.example.ikaru.footballapps.R
import com.google.gson.Gson

import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchListFragment: Fragment(), AnkoComponent<Context>, MatchListView {
    private val matches: MutableList<Match> = mutableListOf()
    private val leagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: MatchListPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var matchesList: RecyclerView
    private lateinit var adapter: MatchListAdapter
    private lateinit var spinner: Spinner
    private var fixture = 1
    private var leagueId = "4328"   //EPL

    companion object {
        fun createFragment(fixture: Int, leagueId: String): MatchListFragment{
            val fragment = MatchListFragment()
            fragment.fixture = fixture
            fragment.leagueId = leagueId

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val gson = Gson()
        presenter = MatchListPresenter(this, gson, fixture)
        presenter.getLeague()

        adapter = MatchListAdapter(matches){
        //    untuk nanti
        }

        matchesList.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getList(leagueId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(context!!))
    }

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        linearLayout {
            orientation = android.widget.LinearLayout.VERTICAL
            lparams(width = matchParent, height = matchParent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = R.id.league_spin

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val league = spinner.selectedItem as League

                        leagueId = league.leagueId.orEmpty()
                        if(leagueId.isNotEmpty()){
                            presenter.getList(leagueId)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }
            }.lparams(width = matchParent, height = wrapContent)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                matchesList = recyclerView {
                    id = R.id.rv_match_list
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = android.support.v7.widget.LinearLayoutManager(ctx)
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showLeague(data: List<League>) {
        hideLoading()
        leagues.clear()
        leagues.addAll(data)

        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, leagues)
        spinner.adapter = spinnerAdapter

        spinner.setSelection(spinnerAdapter.getPosition(League("4328", "English Premier League")))
    }

    override fun showList(data: List<Match>) {
        hideLoading()
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }


}