package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.repository.interfaces.CityLoaderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class LoadCitiesUseCase constructor(
    private val cityLoaderRepository: CityLoaderRepository
) {
    suspend fun invoke(): Flow<List<City>> {
        val result = cityLoaderRepository.load()
        return flow { emit(result) }
    }
}