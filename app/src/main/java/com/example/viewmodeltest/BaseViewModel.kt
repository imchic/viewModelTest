package com.example.viewmodeltest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getCoffeeList() = viewModelScope.launch {
        repository.getCoffeeList()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("BaseViewModel", "onCleared()")
    }

}