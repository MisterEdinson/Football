package com.example.football.repository

import com.example.football.data.host.RetrofitInstance
import com.example.football.data.host.model.Response

class Repository {
    suspend fun getCompetition(): Response {
        return RetrofitInstance.api.getCompetition()
    }
}