package com.example.ikaru.footballapps.Teams

import com.example.ikaru.footballapps.Model.League
import com.example.ikaru.footballapps.Model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<Team>)
    fun showLeague(data: List<League>)
}