package com.example.ikaru.footballapps.Match

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.ikaru.footballapps.Model.Match
import com.example.ikaru.footballapps.R
import com.example.ikaru.footballapps.Util.changeFormatDate
import com.example.ikaru.footballapps.Util.dateTimeToFormat
import com.example.ikaru.footballapps.Util.strToDate
import com.example.ikaru.footballapps.Util.toGMTFormat
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MatchListAdapter(private val matches: List<Match>, private val listener: (Match) -> Unit): RecyclerView.Adapter<MatchListAdapter.EventViewHolder>(){
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
        private val btnNotify: ImageButton = itemView.find(R.id.btn_notif)

        @SuppressLint("SimpleDateFormat")
        fun bindItem(match: Match , listener: (Match) -> Unit){
            val date = strToDate(match.dateEvent)
            val dateTime = toGMTFormat(match.dateEvent,match.strTime)
            eventDate.text = changeFormatDate(date)
            eventTime.text = SimpleDateFormat("HH:mm").format(dateTime)

            homeTeam.text = match.strHomeTeam
            homeScore.text = match.intHomeScore

            awayTeam.text = match.strAwayTeam
            awayScore.text = match.intAwayScore

            if(dateTime!!.after(Date())){
                btnNotify.visibility = View.VISIBLE
            }

            itemView.onClick { listener(match) }
            btnNotify.onClick {
                val intent = Intent(Intent.ACTION_INSERT)
                intent.type = "vnd.android.cursor.item/event"

                val dateTime = match.dateEvent+ " " + match.strTime
                Log.d("dateTime", "DateTime adalah " + dateTime)
                val startTime = dateTime.dateTimeToFormat()
                Log.d("startTime", "startTime adalah " + startTime)
                val endTime = startTime + TimeUnit.MINUTES.toMillis(90)
                Log.d("endTime", "endTime adalah " + endTime)

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
                intent.putExtra(CalendarContract.CalendarAlerts.ALARM_TIME, TimeUnit.MINUTES.toMillis(90))

                intent.putExtra(
                    CalendarContract.Events.TITLE,
                    "${match.strHomeTeam} vs ${match.strAwayTeam}")

                intent.putExtra(
                    CalendarContract.Events.DESCRIPTION,
                    "Reminder ${match.strHomeTeam} and ${match.strAwayTeam} from My Football App")


                itemView.context.startActivity(intent)
            }
        }
    }

}