package com.example.ikaru.footballapps.Api

import com.example.ikaru.footballapps.BuildConfig

object TheSportDBApi {

    private fun urlBuild(path: String, id: String? = null): String{
        val url = BuildConfig.BASE_URL+"api/v1/json/"+BuildConfig.TSDB_API_KEY+"/"+path
        return if(id.isNullOrEmpty()) url else url+"?id="+id
    }

    fun getPrevSchedule(id: String?) = urlBuild("eventspastleague.php", id)
    fun getNextSchedule(id: String?) = urlBuild("eventsnextleague.php", id)
    fun getListLeague() = urlBuild("search_all_leagues.php?s=Soccer")
    fun getMatchDetail(id: String?) = urlBuild("lookupevent.php", id)
    fun getTeams(query: String?) = urlBuild("searchteams.php?t="+query)
    fun getTeamDetail(id: String?) = urlBuild("lookupteam.php", id)
}