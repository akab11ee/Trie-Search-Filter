package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.repository.interfaces.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class SearchCityUseCase constructor(
    private val cityRepository: CityRepository
) {
    suspend fun invoke(query: String): Flow<List<City>> {
        val result = cityRepository.search(query)
        return flow { emit(result) }
    }
}