package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.repository.interfaces.ICityLoaderRepository

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class LoadCitiesUseCase constructor(
    private val cityLoaderRepository: ICityLoaderRepository
) {
    suspend operator fun invoke(): List<City> = cityLoaderRepository.load()
}