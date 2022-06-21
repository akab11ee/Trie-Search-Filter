package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.repository.interfaces.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class GetAllCityUseCase constructor(
    private val cityRepository: CityRepository
) {
    suspend fun invoke(): Flow<List<City>> {
        val result = cityRepository.getAll()
        return flow { emit(result) }
    }
}