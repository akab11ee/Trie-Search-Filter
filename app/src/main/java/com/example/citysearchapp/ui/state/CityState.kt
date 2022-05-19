package com.example.citysearchapp.ui.state

import com.example.citysearchapp.data.entity.City

data class CityState(
    val listCity: List<City> = emptyList(),
    val loading: Boolean = false,
    val emptyView: Boolean = listCity.isEmpty(),
    val retry: Boolean = false
)