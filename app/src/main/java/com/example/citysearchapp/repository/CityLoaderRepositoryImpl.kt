package com.example.citysearchapp.repository

import com.example.citysearchapp.data.FileCityLoader
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.repository.interfaces.CityLoaderRepository
import com.example.citysearchapp.utils.mapper.mapToDomainCity

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class CityLoaderRepositoryImpl constructor(private val fileCityLoader: FileCityLoader) :
    CityLoaderRepository {

    override suspend fun load(): List<City> {
        return fileCityLoader.load().mapToDomainCity()
    }

}