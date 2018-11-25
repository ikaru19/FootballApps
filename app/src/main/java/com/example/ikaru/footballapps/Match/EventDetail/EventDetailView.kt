package  com.example.ikaru.footballapps.Match.EventDetail

import com.example.ikaru.footballapps.Model.Match
import com.example.ikaru.footballapps.Model.Team

interface EventDetailView {
        fun showLoading()
        fun hideLoading()
        fun showAll(match: Match, home : Team, away : Team)

}