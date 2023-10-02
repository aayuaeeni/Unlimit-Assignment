package com.raju.unlimit.data.remote.dto
import com.google.gson.annotations.SerializedName
import com.raju.unlimit.domain.model.JokesDetail

data class JokesDto(
    @SerializedName("joke")
    val joke: String
)
fun JokesDto.toJokesDetail(): JokesDetail {
    return JokesDetail(joke=joke)
}