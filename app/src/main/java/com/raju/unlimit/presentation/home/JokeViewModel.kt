package com.raju.unlimit.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.raju.unlimit.common.Resource
import com.raju.unlimit.domain.model.JokesDetail
import com.raju.unlimit.domain.useCase.getJoke.GetJokeUseCase
import com.raju.unlimit.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getJokeUseCase: GetJokeUseCase
) : BaseViewModel() {
    private val jokeList = mutableListOf<JokesDetail>()
    private val executorService = Executors.newSingleThreadScheduledExecutor()

    private val _jokesLD: MutableLiveData<JokeState> = MutableLiveData()
    val jokesLD: LiveData<JokeState>
        get() = _jokesLD

    init {
        executorService.scheduleAtFixedRate({
            getJoke()
        }, 0, 1, TimeUnit.MINUTES)
    }

    private fun getJoke() {
        getJokeUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (jokeList.size >= 10) {
                        jokeList.removeAt(0)
                    }
                    result.data?.let { jokeList.add(it) }
                    if (jokeList.size !=0){
                        _jokesLD.value = JokeState(jokes = jokeList)
                    }
                }
                is Resource.Error -> {
                    _jokesLD.value = JokeState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _jokesLD.value = JokeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        executorService.shutdown()
        jokeList.clear()
        super.onCleared()
    }
}