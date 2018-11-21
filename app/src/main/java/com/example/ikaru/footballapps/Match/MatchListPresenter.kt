package com.example.ikaru.footballapps.Match
import com.google.gson.Gson
import com.example.ikaru.footballapps.Api.ApiRepository
import com.example.ikaru.footballapps.Api.TheSportDBApi
import com.example.ikaru.footballapps.Model.LeagueResponse
import com.example.ikaru.footballapps.Model.MatchResponse
import com.example.ikaru.footballapps.Util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchListPresenter(private val view: MatchListView,
                           private val gson: Gson,
                           private val fixture: Int = 1,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()){

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

    fun getList(id: String?){
        view.showLoading()

        val api = if (fixture == 1) TheSportDBApi.getPrevSchedule(id) else TheSportDBApi.getNextSchedule(id)

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), MatchResponse::class.java)
            }
            view.showList(data.await().matches)
            view.hideLoading()
        }
    }
}