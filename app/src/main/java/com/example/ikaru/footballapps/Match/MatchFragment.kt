package com.example.ikaru.footballapps.Match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ikaru.footballapps.Adapter.ViewPagerAdapter
import com.example.ikaru.footballapps.R.layout.fragment_match
import kotlinx.android.synthetic.main.fragment_match.*

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

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }

    private fun setupViewPager(viewPager: ViewPager){
            val adapter = ViewPagerAdapter(childFragmentManager)
            adapter.addFrag(MatchListFragment.createFragment(2, leagueId), "NEXT")
            adapter.addFrag(MatchListFragment.createFragment(1, leagueId), "LAST")
            viewPager.adapter = adapter
    }
}