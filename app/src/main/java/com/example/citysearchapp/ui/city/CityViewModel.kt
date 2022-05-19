package com.example.citysearchapp.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.citysearchapp.base.BaseViewModel
import com.example.citysearchapp.ui.event.CityEvent
import com.example.citysearchapp.ui.state.CityState
import com.example.citysearchapp.usecase.GetAllCityUseCase
import com.example.citysearchapp.usecase.InsertCityUseCase
import com.example.citysearchapp.usecase.LoadCitiesUseCase
import com.example.citysearchapp.usecase.SearchCityUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class CityViewModel constructor(
    private val loadCitiesUseCase: LoadCitiesUseCase,
    private val insertCityUseCase: InsertCityUseCase,
    private val getAllCityUseCase: GetAllCityUseCase,
    private val searchCityUseCase: SearchCityUseCase,
    dispatcher: CoroutineDispatcher
) :
    BaseViewModel(dispatcher) {

    private val _state = MutableLiveData(CityState(loading = true))
    val state: LiveData<CityState> = _state


    fun onStart() {
        loadCities()
    }

    fun event(event: CityEvent) {
        when (event) {
            is CityEvent.LoadCities -> {
                loadCities()
            }
            is CityEvent.Search -> {
                viewModelScope.launch(getAppDispatcher()) {
                    _state.value = CityState(loading = true)
                    val result = searchCityUseCase(event.query)
                    _state.value = CityState(listCity = result)
                }
            }
        }
    }

    private fun loadCities() {
        viewModelScope.launch(getAppDispatcher()) {

            val persons = loadCitiesUseCase()
            val loaded = insertCityUseCase(persons)

            if (loaded) {
                val result = getAllCityUseCase()
                _state.value = CityState(listCity = result)
            } else {
                _state.value = CityState(retry = true)
            }
        }
    }
}