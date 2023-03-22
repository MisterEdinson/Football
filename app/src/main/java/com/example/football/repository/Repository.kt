package com.example.football.repository

import com.example.football.data.host.SimpleApi
import com.example.football.data.host.model.General
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val simpleApi: SimpleApi
) {
    suspend fun getCompetition(): Response<General> {
        return simpleApi.getCompetition()
    }
}