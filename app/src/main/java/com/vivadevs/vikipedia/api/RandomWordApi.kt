package com.vivadevs.vikipedia.api

import retrofit2.Response
import retrofit2.http.GET

interface RandomWordApi {
    @GET("word")
    suspend fun getRandomWord(): Response<List<String>>
}