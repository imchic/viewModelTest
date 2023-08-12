package com.example.viewmodeltest

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
) : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count = _count

    fun increaseCount() = viewModelScope.launch {
        _count.value++
    }

    fun decreaseCount() = viewModelScope.launch {
        _count.value--
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("BaseViewModel", "onCleared()")
    }

}

class BaseViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            return BaseViewModel() as T
        }
        throw IllegalArgumentException("잘못된 ViewModel 클래스")
    }

}