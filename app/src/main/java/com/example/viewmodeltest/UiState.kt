package com.example.viewmodeltest

sealed class UiState {
    data class Success(val data: List<Coffee>): UiState()
    object Loading: UiState()
    data class Error(val message: String): UiState()
}
