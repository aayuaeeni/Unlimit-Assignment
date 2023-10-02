package com.raju.unlimit.data.repository
import com.raju.unlimit.data.remote.JokesApi
import com.raju.unlimit.data.remote.dto.JokesDto
import com.raju.unlimit.domain.repository.JokeRepository
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val api: JokesApi
) : JokeRepository {

    override suspend fun getJoke(): JokesDto {
        return api.getJoke()
    }
}