package com.example.ikaru.footballapps.Match.Search

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ikaru.footballapps.Match.EventUI
import com.example.ikaru.footballapps.Match.MatchListAdapter
import com.example.ikaru.footballapps.Model.Match
import com.example.ikaru.footballapps.R
import com.example.ikaru.footballapps.Util.changeFormatDate
import com.example.ikaru.footballapps.Util.strToDate
import com.example.ikaru.footballapps.Util.toGMTFormat
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat

class MatchSearchAdapter(private val matches: List<Match>, private val listener: (Match) -> Unit): RecyclerView.Adapter<MatchSearchAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = matches.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) = holder.bindItem(matches[position], listener)

    class EventViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val eventDate: TextView = itemView.find(R.id.date)
        private val eventTime: TextView = itemView.find(R.id.time)
        private val homeTeam: TextView = itemView.find(R.id.homeTeam)
        private val homeScore: TextView = itemView.find(R.id.homeScore)
        private val awayTeam: TextView = itemView.find(R.id.awayTeam)
        private val awayScore: TextView = itemView.find(R.id.awayScore)

        @SuppressLint("SimpleDateFormat")
        fun bindItem(match: Match, listener: (Match) -> Unit){
            val date = strToDate(match.dateEvent)
            val dateTime = toGMTFormat(match.dateEvent, match.strTime)
            eventDate.text = changeFormatDate(date)
            eventTime.text = SimpleDateFormat("HH:mm").format(dateTime)

            homeTeam.text = match.strHomeTeam
            homeScore.text = match.intHomeScore

            awayTeam.text = match.strAwayTeam
            awayScore.text = match.intAwayScore

            itemView.onClick { listener(match) }
        }
    }

}