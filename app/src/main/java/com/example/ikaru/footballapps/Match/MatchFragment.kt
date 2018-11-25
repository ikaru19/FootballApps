package com.example.ikaru.footballapps.Match

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ikaru.footballapps.Adapter.ViewPagerAdapter
import com.example.ikaru.footballapps.Match.Search.MatchSearch
import com.example.ikaru.footballapps.R
import com.example.ikaru.footballapps.R.id.search_menu
import com.example.ikaru.footballapps.R.layout.fragment_match
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.find

class MatchFragment: Fragment() {
    var leagueId = "4328"   //EPL

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager(matches_viewpager)

        matches_tabs.setupWithViewPager(matches_viewpager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragment_match, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = find<Toolbar>(R.id.matches_toolbar)
        toolbar.inflateMenu(R.menu.search)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                search_menu -> {
                    val intent = Intent(context, MatchSearch::class.java)
                    startActivity(intent)
                }
            }

            true
        }
    }

    private fun setupViewPager(viewPager: ViewPager){
            val adapter = ViewPagerAdapter(childFragmentManager)
            adapter.addFrag(MatchListFragment.createFragment(2, leagueId), "NEXT")
            adapter.addFrag(MatchListFragment.createFragment(1, leagueId), "LAST")
            viewPager.adapter = adapter
    }
}