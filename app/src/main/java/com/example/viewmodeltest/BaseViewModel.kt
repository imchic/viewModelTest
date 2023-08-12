package com.example.viewmodeltest

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_TEMP = "temp"
    }

    fun getTemp(): String? {
        return savedStateHandle.get<String>(KEY_TEMP)
    }

    fun saveTemp(temp: String) {
        savedStateHandle.set(KEY_TEMP, temp)
    }

    private val _count = MutableStateFlow(0)
    val count = _count

    fun increaseCount() {
        _count.value++
    }

    fun decreaseCount() {
        _count.value--
    }


}

class BaseViewModelFactory @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            return BaseViewModel(savedStateHandle) as T
        }
        throw IllegalArgumentException("잘못된 ViewModel 클래스")
    }

}