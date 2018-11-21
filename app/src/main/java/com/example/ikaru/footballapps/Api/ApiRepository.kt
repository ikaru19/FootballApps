package com.example.ikaru.footballapps.Api

import java.net.URL

class ApiRepository {
    fun doRequest(url: String) = URL(url).readText()
}