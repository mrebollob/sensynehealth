package com.mrebollob.codetest.presentation.features.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.mrebollob.codetest.R
import com.mrebollob.codetest.databinding.ActivityMainBinding
import com.mrebollob.codetest.di.InjectorUtils
import com.mrebollob.codetest.presentation.ErrorState
import com.mrebollob.codetest.presentation.LoadingState
import com.mrebollob.codetest.presentation.ScreenState
import com.mrebollob.codetest.presentation.extension.snack
import com.mrebollob.codetest.presentation.extension.visible
import com.mrebollob.codetest.presentation.features.details.DetailsActivity

class MainActivity : AppCompatActivity() {

    private val listViewModel: ListViewModel by viewModels {
        InjectorUtils.provideAppListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        initUI(binding)
    }

    private fun initUI(binding: ActivityMainBinding) {
        val adapter = HospitalsAdapter(
            clickListener = { id -> DetailsActivity.open(this, id) }
        )

        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.title = ""
            appList.adapter = adapter
            viewModel = listViewModel

            listViewModel.hospitals.observe(this@MainActivity) { apps ->
                emptyView.visible(apps.isEmpty())
                appList.visible(apps.isNotEmpty())
                adapter.submitList(apps)
            }

            listViewModel.screenState.observe(this@MainActivity) { state ->
                handleScreenState(binding, state)
            }
        }
    }

    private fun handleScreenState(binding: ActivityMainBinding, state: ScreenState) {
        binding.loadingView.visible(state == LoadingState)

        if (state is ErrorState) {
            binding.appList.snack(message = "Error",
                length = Snackbar.LENGTH_INDEFINITE,
                actionText = "Retry",
                action = { listViewModel.loadApps() })
        }
    }
}
