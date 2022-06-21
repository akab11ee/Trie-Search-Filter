package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.repository.interfaces.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class InsertCityUseCase constructor(
    private val cityRepository: CityRepository
) {

    suspend fun invoke(cityList: List<City>): Flow<Boolean> {
        val result = cityRepository.insert(cityList)
        return flow { emit(result) }
    }
}