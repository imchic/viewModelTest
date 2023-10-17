package com.example.viewmodeltest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val repository: BaseRepository,
) : ViewModel() {

    fun increaseCount() = viewModelScope.launch {
        repository.increaseCount()
    }

    fun decreaseCount() = viewModelScope.launch {
        repository.decreaseCount()
    }

    val getCount = repository.getCount

    val iceCoffeeList: StateFlow<UiState> = flow {
        try {
            val result = repository.getIceCoffeeList()
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO).stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = UiState.Loading
    )

    val hotCoffeeList: StateFlow<UiState> = flow {
        try {
            val result = repository.getHotCoffeeList()
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO).stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = UiState.Loading
    )

    override fun onCleared() {
        super.onCleared()
        Timber.d("BaseViewModel", "onCleared()")
    }

}