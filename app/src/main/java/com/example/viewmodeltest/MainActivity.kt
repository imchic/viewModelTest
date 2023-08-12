package com.example.viewmodeltest

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.viewmodeltest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: BaseViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding?.apply {

            button.setOnClickListener {
                viewModel.increaseCount()
                Snackbar.make(it, "count: ${viewModel.count.value}", Snackbar.LENGTH_SHORT).show()
            }

            button2.setOnClickListener {
                viewModel.decreaseCount()
                Snackbar.make(it, "count: ${viewModel.count.value}", Snackbar.LENGTH_SHORT).show()
            }

        }

        lifecycleScope.launch {
            viewModel.count.collect { count ->
                binding?.textView2?.text = count.toString()
            }
        }

    }


}