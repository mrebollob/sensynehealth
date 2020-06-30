package com.dhis.store.presentation.features.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.dhis.store.R
import com.dhis.store.core.entity.FilterType
import com.dhis.store.databinding.ActivityMainBinding
import com.dhis.store.di.InjectorUtils
import com.dhis.store.presentation.ErrorState
import com.dhis.store.presentation.LoadingState
import com.dhis.store.presentation.ScreenState
import com.dhis.store.presentation.extension.snack
import com.dhis.store.presentation.extension.toast
import com.dhis.store.presentation.extension.visible
import com.dhis.store.presentation.features.details.DetailsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.view_filters.*

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

            listViewModel.apps.observe(this@MainActivity) { apps ->
                emptyView.visible(apps.isEmpty())
                appList.visible(apps.isNotEmpty())
                adapter.submitList(apps)
            }

            listViewModel.screenState.observe(this@MainActivity) { state ->
                handleScreenState(binding, state)
            }

            listViewModel.hospitalFilter.observe(this@MainActivity) { appFilter ->

                if (appFilter.startDate != null &&
                    appFilter.endDate != null
                ) {
                    date_filter_value.text = getString(
                        R.string.list_screen_filter_by_publish_date_value,
                        appFilter.startDate,
                        appFilter.endDate
                    )
                    clear_date_selector.visible(true)
                } else {
                    date_filter_value.text = "-"
                    clear_date_selector.visible(false)
                }
            }

            listViewModel.filters.observe(this@MainActivity) { filters ->
                author_filter_view.visible(filters.contains(FilterType.AUTHOR))

                if (filters.contains(FilterType.DATE)) {
                    clear_date_selector.setOnClickListener {
                        listViewModel.onDateFilterChanged(null, null)
                    }
                }
            }
        }
    }

    private fun handleScreenState(binding: ActivityMainBinding, state: ScreenState) {
        when (state) {
            LoadingState -> toast("Loading")
            is ErrorState -> binding.appList.snack(message = "Error",
                length = Snackbar.LENGTH_INDEFINITE,
                actionText = "Retry",
                action = { listViewModel.loadApps() })
        }
    }
}
