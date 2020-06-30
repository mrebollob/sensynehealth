package com.dhis.store.presentation.features.list

import android.os.Bundle
import android.widget.SeekBar
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
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment
import kotlinx.android.synthetic.main.view_filters.*
import java.util.*

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

            listViewModel.appFilter.observe(this@MainActivity) { appFilter ->

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
                size_filter_view.visible(filters.contains(FilterType.SIZE))
                date_filter_value.visible(filters.contains(FilterType.DATE))

                if (filters.contains(FilterType.DATE)) {
                    date_selector.setOnClickListener {
                        showDateSelector()
                    }

                    clear_date_selector.setOnClickListener {
                        listViewModel.onDateFilterChanged(null, null)
                    }
                }

                filters_view.visible(filters.isNotEmpty())

                size_filter_input.setOnSeekBarChangeListener(object :
                    SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        size_filter_value.text = getString(R.string.app_size_format, progress)
                        listViewModel.onSizeFilterChanged(progress)
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        //NA
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        //NA
                    }
                })
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

    private fun showDateSelector() {
        val smoothDateRangePickerFragment =
            SmoothDateRangePickerFragment.newInstance { _, yearStart, monthStart, dayStart,
                                                        yearEnd, monthEnd, dayEnd ->

                val startCalendar = Calendar.getInstance()
                startCalendar.set(Calendar.YEAR, yearStart)
                startCalendar.set(Calendar.MONTH, monthStart)
                startCalendar.set(Calendar.DAY_OF_MONTH, dayStart)

                val endCalendar = Calendar.getInstance()
                endCalendar.set(Calendar.YEAR, yearEnd)
                endCalendar.set(Calendar.MONTH, monthEnd)
                endCalendar.set(Calendar.DAY_OF_MONTH, dayEnd)

                listViewModel.onDateFilterChanged(startCalendar.time, endCalendar.time)
            }

        smoothDateRangePickerFragment.maxDate = Calendar.getInstance()
        smoothDateRangePickerFragment.show(fragmentManager, "DateRangePicker")
    }
}
