package com.raju.unlimit.presentation.home

import com.raju.unlimit.domain.model.JokesDetail
data class JokeState(
    val isLoading: Boolean = false,
    val jokes: List<JokesDetail> = emptyList(),
    val error: String = ""
)
