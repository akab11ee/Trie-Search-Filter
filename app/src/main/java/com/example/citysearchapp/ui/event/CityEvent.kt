package com.example.citysearchapp.ui.event

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

sealed class CityEvent {
    object LoadCities : CityEvent()
    data class Search(val query: String) : CityEvent()
}