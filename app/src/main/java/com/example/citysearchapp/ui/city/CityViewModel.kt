package com.example.citysearchapp.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.citysearchapp.base.BaseViewModel
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.ui.event.CityEvent
import com.example.citysearchapp.ui.state.CityState
import com.example.citysearchapp.usecase.GetAllCityUseCase
import com.example.citysearchapp.usecase.InsertCityUseCase
import com.example.citysearchapp.usecase.LoadCitiesUseCase
import com.example.citysearchapp.usecase.SearchCityUseCase
import com.example.citysearchapp.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class CityViewModel constructor(
    private val loadCitiesUseCase: LoadCitiesUseCase,
    private val insertCityUseCase: InsertCityUseCase,
    private val getAllCityUseCase: GetAllCityUseCase,
    private val searchCityUseCase: SearchCityUseCase,
    dispatcher: DispatcherProvider
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
                viewModelScope.launch(coroutineExceptionHandler) {
                    _state.value = CityState(loading = true)
                    searchCityUseCase.invoke(event.query).flowOn(getAppDispatcher().io())
                        .collect { listCity ->
                            _state.value = CityState(listCity = listCity)
                        }
                }
            }
        }
    }

    private fun insertCities(listCities: List<City>) {
        viewModelScope.launch(coroutineExceptionHandler) {
            insertCityUseCase.invoke(listCities).flowOn(getAppDispatcher().io()).collect { loaded ->
                getAllCities(loaded)
            }
        }
    }

    private fun getAllCities(loaded: Boolean) {
        if (loaded) {
            viewModelScope.launch(coroutineExceptionHandler) {
                getAllCityUseCase.invoke().flowOn(getAppDispatcher().io()).collect { result ->
                    _state.value = CityState(listCity = result)
                }
            }
        } else {
            _state.value = CityState(retry = true)
        }
    }

    private fun loadCities() {
        viewModelScope.launch(coroutineExceptionHandler) {

            loadCitiesUseCase.invoke().flowOn(getAppDispatcher().io()).collect { listCity ->
                insertCities(listCity)
            }
        }
    }
}