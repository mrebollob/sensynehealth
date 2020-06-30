package com.dhis.store.presentation.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhis.store.core.HospitalFilter
import com.dhis.store.core.SensyneRepository
import com.dhis.store.core.entity.Failure
import com.dhis.store.core.entity.Hospital
import com.dhis.store.presentation.ErrorState
import com.dhis.store.presentation.LoadingState
import com.dhis.store.presentation.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ListViewModel(
    private val sensyneRepository: SensyneRepository
) : ViewModel() {

    private var hospitalsData: MutableList<Hospital> = mutableListOf()

    private val _hospitalFilter = MutableLiveData<HospitalFilter>()
    val hospitalFilter: LiveData<HospitalFilter> get() = _hospitalFilter

    private val _hospitals = MutableLiveData<List<Hospital>>()
    val hospitals: LiveData<List<Hospital>> get() = _hospitals

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState>
        get() = _screenState

    init {
        loadApps()
        _hospitalFilter.value = HospitalFilter()
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
                    hospitalsData.clear()
                    hospitalsData.addAll(apps)
                    _hospitals.value = apps
                }
        }
    }

    fun onNameFilterChanged(text: CharSequence) {
        _hospitalFilter.value = _hospitalFilter.value?.copy(name = text.toString())
        applyFilter()
    }

    private fun applyFilter() {
        _hospitals.value = hospitalsData.filter {
            _hospitalFilter.value?.filter(it) ?: true
        }
    }
}
