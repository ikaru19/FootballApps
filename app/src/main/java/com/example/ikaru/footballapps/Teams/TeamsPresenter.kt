package com.example.ikaru.footballapps.Teams

import com.example.ikaru.footballapps.Api.ApiRepository
import com.example.ikaru.footballapps.Api.TheSportDBApi
import com.example.ikaru.footballapps.Model.LeagueResponse
import com.example.ikaru.footballapps.Model.TeamResponse
import com.example.ikaru.footballapps.Util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter( private val view : TeamsView ,
                      private val gson : Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    val apiRepository = ApiRepository()

    fun getLeague(){
        view.showLoading()

        val api = TheSportDBApi.getListLeague()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), LeagueResponse::class.java)
            }

            view.showLeague(data.await().countrys)
            view.hideLoading()
        }
    }

    fun getTeam(query:String?,type : Int =1){
        view.showLoading()


        val api = if(type == 1) TheSportDBApi.getListTeams(query) else TheSportDBApi.searchTeams(query)

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), TeamResponse::class.java)
            }

            view.showList(data.await().teams)
            view.hideLoading()
        }
    }


}