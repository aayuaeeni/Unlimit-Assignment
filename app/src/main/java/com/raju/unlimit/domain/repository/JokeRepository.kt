package com.raju.unlimit.domain.repository

import com.raju.unlimit.data.remote.dto.JokesDto

interface JokeRepository {
    suspend fun getJoke(): JokesDto
}