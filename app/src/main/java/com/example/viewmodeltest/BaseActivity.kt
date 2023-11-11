package com.example.viewmodeltest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class BaseActivity<VB : ViewDataBinding, VM: ViewModel>() : AppCompatActivity() {

    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    var binding: VB? = null

    abstract val viewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())

        Log.d("BaseActivity", "onCreate()")

        binding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        binding?.lifecycleOwner = this

    }

    abstract fun getLayoutResourceId(): Int


    override fun onStop() {
        super.onStop()
        Log.d("BaseActivity", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BaseActivity", "onDestroy()")
        viewModelScope.cancel()
    }
}