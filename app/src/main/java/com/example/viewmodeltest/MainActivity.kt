package com.example.viewmodeltest

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.viewmodeltest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModels()

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding?.apply {

            button.setOnClickListener {
                viewModel.increaseCount()
            }

            button2.setOnClickListener {
                viewModel.decreaseCount()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                launch {
                    viewModel.getCount.observe(this@MainActivity){
                        Timber.d("getCount: $it")
                        binding?.textView2?.text = it.toString()
                    }
                }

                launch {
                    viewModel.getCoffeeList()
                }
            }
        }
    }
}