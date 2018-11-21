package com.example.ikaru.footballapps.Match

import com.example.ikaru.footballapps.Model.League
import com.example.ikaru.footballapps.Model.Match

interface MatchListView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<Match>)
    fun showLeague(data: List<League>)
}