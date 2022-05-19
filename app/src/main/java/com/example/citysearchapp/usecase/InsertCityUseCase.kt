package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.repository.interfaces.ICityRepository

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class InsertCityUseCase constructor(
    private val cityRepository: ICityRepository
) {

    suspend operator fun invoke(cityList: List<City>): Boolean =
        cityRepository.insert(cityList)
}