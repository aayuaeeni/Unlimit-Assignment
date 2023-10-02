package com.raju.unlimit.data.remote

import com.raju.unlimit.data.remote.dto.JokesDto
import retrofit2.http.GET

interface JokesApi {

    @GET("api?format=json")
    suspend fun getJoke(): JokesDto
}