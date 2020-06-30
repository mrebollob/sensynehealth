package com.mrebollob.codetest.presentation.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrebollob.codetest.core.HospitalFilter
import com.mrebollob.codetest.core.SensyneRepository
import com.mrebollob.codetest.core.entity.Failure
import com.mrebollob.codetest.core.entity.Hospital
import com.mrebollob.codetest.presentation.ErrorState
import com.mrebollob.codetest.presentation.LoadingState
import com.mrebollob.codetest.presentation.ReadyState
import com.mrebollob.codetest.presentation.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ListViewModel(
    private val sensyneRepository: SensyneRepository
) : ViewModel() {

    private var hospitalsData: MutableList<Hospital> = mutableListOf()
    private var hospitalFilter = HospitalFilter()

    private val _hospitals = MutableLiveData<List<Hospital>>()
    val hospitals: LiveData<List<Hospital>> get() = _hospitals

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState>
        get() = _screenState

    init {
        _screenState.value = LoadingState
        loadApps()
    }

    @ExperimentalCoroutinesApi
    fun loadApps() {
        viewModelScope.launch {
            sensyneRepository.getHospitals()
                .onStart { _screenState.value = LoadingState }
                .catch { _screenState.value = ErrorState(Failure.GENERIC_ERROR) }
                .collect { apps ->
                    hospitalsData.clear()
                    hospitalsData.addAll(apps)
                    _hospitals.value = apps
                    _screenState.value = ReadyState
                }
        }
    }

    fun onNameFilterChanged(text: CharSequence) {
        hospitalFilter = hospitalFilter.copy(name = text.toString())
        applyFilter()
    }

    fun onShowNHSFilterChanged(showNHS: Boolean) {
        hospitalFilter = hospitalFilter.copy(showNHS = showNHS)
        applyFilter()
    }

    private fun applyFilter() {
        _hospitals.value = hospitalsData.filter {
            hospitalFilter.filter(it)
        }
    }
}
