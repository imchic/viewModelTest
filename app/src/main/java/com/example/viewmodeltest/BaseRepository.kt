package com.example.viewmodeltest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val apiService: ApiService
) {

    val getCount = MutableLiveData(0)
    fun increaseCount() {
        getCount.value = getCount.value?.plus(1)
    }
    fun decreaseCount() {
        getCount.value = getCount.value?.minus(1)
    }
    suspend fun getIceCoffeeList(): List<Coffee> {
        apiService.getIceCoffeeList().apply {
            return if (isSuccessful){
                body() ?: emptyList()
            } else {
                Log.d("BaseRepository", "getCoffeList: ${errorBody()}")
                emptyList()
            }
        }
    }

    suspend fun getHotCoffeeList(): List<Coffee> {
        apiService.getHotCoffeeList().apply {
            return if (isSuccessful){
                body() ?: emptyList()
            } else {
                Log.d("BaseRepository", "getCoffeList: ${errorBody()}")
                emptyList()
            }
        }
    }


}