package com.example.ikaru.footballapps.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    var idEvent: String? = null,
    var dateEvent: String? = null,
    var strSport: String? = null,
    var strTime: String? = null,
    var strEvent: String? = null,
    var strHomeTeam: String? = null,
    var strAwayTeam: String? = null,
    var intHomeScore: String? = null,
    var intAwayScore: String? = null,
    var idHomeTeam: String? = null,
    var idAwayTeam: String? = null,
    var strHomeGoalDetails: String? = null,
    var strAwayGoalDetails: String? = null,
    var intHomeShots: String? = null,
    var intAwayShots: String? = null,
    var strHomeYellowCards: String? = null,
    var strAwayYellowCards: String? = null,
    var strHomeRedCards: String? = null,
    var strAwayRedCards: String? = null,
    var strHomeLineupGoalkeeper: String? = null,
    var strAwayLineupGoalkeeper: String? = null,
    var strHomeLineupDefense: String? = null,
    var strAwayLineupDefense: String? = null,
    var strHomeLineupMidfield: String? = null,
    var strAwayLineupMidfield: String? = null,
    var strHomeLineupForward: String? = null,
    var strAwayLineupForward: String? = null,
    var strHomeLineupSubstitutes: String? = null,
    var strAwayLineupSubstitutes: String? = null

    ) : Parcelable