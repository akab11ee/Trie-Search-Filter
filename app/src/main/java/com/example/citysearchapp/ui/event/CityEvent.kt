package com.example.citysearchapp.ui.event

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

sealed class CityEvent {
    object LoadCities : CityEvent()
    data class Search(val query: String) : CityEvent()
}