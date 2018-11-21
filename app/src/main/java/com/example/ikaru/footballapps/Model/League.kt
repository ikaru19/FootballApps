package com.example.ikaru.footballapps.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League ( var leagueId: String? = null, var leagueName: String? = null): Parcelable {

    override fun toString(): String {
        return leagueName.orEmpty()
    }

}