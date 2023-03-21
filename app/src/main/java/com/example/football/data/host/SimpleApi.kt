package com.example.football.data.host


import com.example.football.data.host.model.Response
import retrofit2.http.GET


interface SimpleApi {
    @GET("competitions")
    suspend fun getCompetition(): Response
}

