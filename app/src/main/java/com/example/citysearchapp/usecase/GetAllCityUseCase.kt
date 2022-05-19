package com.example.citysearchapp.usecase

import com.example.citysearchapp.repository.interfaces.ICityRepository

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class GetAllCityUseCase constructor(
    private val cityRepository: ICityRepository
) {
    suspend operator fun invoke() = cityRepository.getAll()
}