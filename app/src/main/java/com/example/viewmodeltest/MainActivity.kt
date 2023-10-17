package com.example.viewmodeltest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.viewmodeltest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

            viewModel.getCount.observe(this@MainActivity){
                Timber.d("getCount: $it")
                binding?.textView2?.text = it.toString()
            }

            repeatOnLifecycle(Lifecycle.State.STARTED){

                launch {
                    viewModel.iceCoffeeList.collectLatest {
                        when(it) {
                            is UiState.Success -> {
                                Timber.d("result: ${it.data}")
                            }
                            is UiState.Loading -> {
                                Timber.d("result: Loading")
                            }
                            is UiState.Error -> {
                                Timber.d("result: ${it.message}")
                            }
                        }
                    }
                }

                launch {
                    viewModel.hotCoffeeList.collectLatest {
                        when(it) {
                            is UiState.Success -> {
                                Timber.d("result: ${it.data}")
                            }
                            is UiState.Loading -> {
                                Timber.d("result: Loading")
                            }
                            is UiState.Error -> {
                                Timber.d("result: ${it.message}")
                            }
                        }
                    }
                }
            }
        }
    }
}