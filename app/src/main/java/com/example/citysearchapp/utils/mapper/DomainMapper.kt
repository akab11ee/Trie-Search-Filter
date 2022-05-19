package com.example.citysearchapp.utils.mapper

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.CityEntity
import com.example.citysearchapp.data.entity.Coordinate

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

fun List<CityEntity>.mapToDomainCity(): List<City> {
    return mutableListOf<City>().apply {
        for (element in this@mapToDomainCity) {
            add(element.mapToDomainCity())
        }
    }
}

private fun CityEntity.mapToDomainCity(): City {
    return City(country, name, id, Coordinate(coord.lon, coord.lat))
}