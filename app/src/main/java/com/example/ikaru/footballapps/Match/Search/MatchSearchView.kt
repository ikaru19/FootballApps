package com.example.ikaru.footballapps.Match.Search

import com.example.ikaru.footballapps.Model.Match

interface MatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun showList(data : List<Match>)
}
