package com.example.ikaru.footballapps.Match.Search

import com.example.ikaru.footballapps.Api.ApiRepository
import com.example.ikaru.footballapps.Api.TheSportDBApi
import com.example.ikaru.footballapps.Model.MatchResponse
import com.example.ikaru.footballapps.Util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchSearchPresenter(private val view:MatchSearchView,
                           private val gson: Gson,
                           private val context : CoroutineContextProvider =  CoroutineContextProvider()) {
    val apiRes = ApiRepository()
    fun getList(query: String?){
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRes.doRequest(TheSportDBApi.searchEvent(query)), MatchResponse::class.java)
            }

            view.showList(data.await().event)
            view.hideLoading()
        }
    }
}