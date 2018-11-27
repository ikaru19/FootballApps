package com.example.ikaru.footballapps.Match.Search

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.LinearLayout
import com.example.ikaru.footballapps.Match.EventDetail.EventDetail
import com.example.ikaru.footballapps.Model.Match
import com.example.ikaru.footballapps.R
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchSearch: AppCompatActivity(), MatchSearchView{
    private var events: MutableList<Match> = mutableListOf()
    private lateinit var searchView: SearchView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var matchesList: RecyclerView
    private lateinit var presenter: MatchSearchPresenter
    private lateinit var adapter: MatchSearchAdapter
    private var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val gson = Gson()
        presenter = MatchSearchPresenter(this,gson)
        adapter = MatchSearchAdapter(events){
            startActivity<EventDetail>("EVENT" to it)
        }

        linearLayout{
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(2)
            leftPadding = dip(5)
            rightPadding = dip(5)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                matchesList = recyclerView {
                    id = R.id.rv_search_list
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }.lparams(width = matchParent, height = matchParent)
        }

        matchesList.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getList(query)
        }

    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showList(data: List<Match>) {
        hideLoading()

        events.clear()
        data.forEach {
            if(it.strSport.equals("Soccer")){
                events.add(it)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.isIconified = false
        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if(text?.length!! > 1) {
                    query = text
                    presenter.getList(query)
                }
                return false
            }

        })

        return true
    }
}