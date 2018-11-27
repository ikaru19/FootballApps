package com.example.ikaru.footballapps.Teams

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ikaru.footballapps.Model.Team
import com.example.ikaru.footballapps.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamsAdapter (private val teams: List<Team>, private val listener: (Team) -> Unit): RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int){
        holder.bindItem(teams[position], listener)
    }

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)

        fun bindItem(teams: Team, listener: (Team) -> Unit){
            Picasso.get().load(teams.strTeamBadge).into(teamBadge)
            teamName.text = teams.strTeam

            itemView.onClick {
                listener(teams)
            }
        }
    }
}