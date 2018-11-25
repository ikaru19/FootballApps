package  com.example.ikaru.footballapps.Match.EventDetail


import com.example.ikaru.footballapps.Api.ApiRepository
import com.example.ikaru.footballapps.Api.TheSportDBApi
import com.example.ikaru.footballapps.Model.MatchResponse
import com.example.ikaru.footballapps.Model.Team
import com.example.ikaru.footballapps.Model.TeamResponse
import com.example.ikaru.footballapps.Util.CoroutineContextProvider
import com.google.gson.Gson
import org.jetbrains.anko.coroutines.experimental.bg
import kotlinx.coroutines.experimental.async


class EventDetailPresenter (private val view: EventDetail,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()){


    val apiRepository = ApiRepository()
    fun getListDetail(eventId : String? , homeId : String? , awayId : String? ) {
        view.showLoading()

        async(context.main) {
            val matchDetail = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatchDetail(eventId)), MatchResponse::class.java)
            }
            val homeTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(homeId)), TeamResponse::class.java)
            }
            val awayTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(awayId)), TeamResponse::class.java)
            }
            view.showAll(matchDetail.await().events.get(0), homeTeam.await().teams.get(0), awayTeam.await().teams.get(0))
                view.hideLoading()


        }
    }

}