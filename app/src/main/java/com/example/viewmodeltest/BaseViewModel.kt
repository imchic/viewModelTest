package com.example.viewmodeltest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val repository: BaseRepository
) : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count = _count

    fun increaseCount() = viewModelScope.launch {
        _count.value = repository.increaseCount(_count.value)
    }

    fun decreaseCount() = viewModelScope.launch {
        _count.value = repository.decreaseCount(_count.value)
    }

    fun resetCount() = viewModelScope.launch {
        _count.value = repository.resetCount()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("BaseViewModel", "onCleared()")
    }

}

class BaseViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            return BaseViewModel(
                repository = BaseRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}