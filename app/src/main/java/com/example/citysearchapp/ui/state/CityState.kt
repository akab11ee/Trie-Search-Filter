package com.example.citysearchapp.ui.state

import com.example.citysearchapp.data.entity.City

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

data class CityState(
    val listCity: List<City> = emptyList(),
    val loading: Boolean = false,
    val emptyView: Boolean = listCity.isEmpty(),
    val retry: Boolean = false
)