package com.dhis.store.presentation.features.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.dhis.store.R
import com.dhis.store.databinding.ActivityMainBinding
import com.dhis.store.di.InjectorUtils
import com.dhis.store.presentation.ErrorState
import com.dhis.store.presentation.LoadingState
import com.dhis.store.presentation.ScreenState
import com.dhis.store.presentation.extension.snack
import com.dhis.store.presentation.extension.visible
import com.dhis.store.presentation.features.details.DetailsActivity
import com.google.android.material.snackbar.Snackbar

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
