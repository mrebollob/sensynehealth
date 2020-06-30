package com.dhis.store.presentation.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhis.store.core.SensyneRepository
import com.dhis.store.core.HospitalFilter
import com.dhis.store.core.entity.Failure
import com.dhis.store.core.entity.FilterType
import com.dhis.store.core.entity.Hospital
import com.dhis.store.presentation.ErrorState
import com.dhis.store.presentation.LoadingState
import com.dhis.store.presentation.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.*

class ListViewModel(
    private val sensyneRepository: SensyneRepository
) : ViewModel() {

    private var appList: MutableList<Hospital> = mutableListOf()

    private val _appFilter = MutableLiveData<HospitalFilter>()
    val hospitalFilter: LiveData<HospitalFilter> get() = _appFilter

    private val _apps = MutableLiveData<List<Hospital>>()
    val apps: LiveData<List<Hospital>> get() = _apps

    private val _filters = MutableLiveData<List<FilterType>>()
    val filters: LiveData<List<FilterType>> get() = _filters

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState>
        get() = _screenState

    init {
        loadApps()
        _appFilter.value = HospitalFilter()
    }

    @ExperimentalCoroutinesApi
    fun loadApps() {
        viewModelScope.launch {
            sensyneRepository.getHospitals()
                .onStart { _screenState.value = LoadingState }
                .catch { exception ->
                    exception.printStackTrace()
                    _screenState.value = ErrorState(Failure.GENERIC_ERROR)
                }
                .collect { apps ->
                    appList.clear()
                    appList.addAll(apps)
                    _apps.value = apps
                }
        }
    }

    fun onAuthorFilterChanged(text: CharSequence) {
        _appFilter.value = _appFilter.value?.copy(author = text.toString())
        applyFilter()
    }

    fun onSizeFilterChanged(maxSize: Int) {
        _appFilter.value = _appFilter.value?.copy(maxSize = maxSize)
        applyFilter()
    }

    fun onDateFilterChanged(startDate: Date?, endDate: Date?) {
        _appFilter.value = _appFilter.value?.copy(startDate = startDate, endDate = endDate)
        applyFilter()
    }

    private fun applyFilter() {
        _apps.value = appList.filter {
            _appFilter.value?.filter(it) ?: true
        }
    }
}
