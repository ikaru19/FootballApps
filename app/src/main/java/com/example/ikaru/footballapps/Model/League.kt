package com.example.ikaru.footballapps.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League ( var idLeague: String? = null, var strLeague: String? = null): Parcelable {

    override fun toString(): String {
        return strLeague.orEmpty()
    }

}