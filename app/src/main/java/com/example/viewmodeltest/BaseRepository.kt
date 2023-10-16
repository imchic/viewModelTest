package com.example.viewmodeltest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
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
    suspend fun getCoffeeList() {
        apiService.getCoffeeList().apply {
            if (isSuccessful){
                body()?.forEach {
                    Timber.d("getCoffeList: $it")
                }
            } else {
                Timber.d("getCoffeList: ${errorBody()}")
            }
        }
    }

    // convert livedata
//    suspend fun getCoffeList(): LiveData<List<Coffee>> {
//        val result = MutableLiveData<List<Coffee>>()
//        apiService.getCoffeeList().apply {
//            if (isSuccessful){
//                result.value = body()
//            } else {
//                Log.d("BaseRepository", "getCoffeList: ${errorBody()}")
//            }
//        }
//        return result
//    }


}