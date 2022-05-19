package com.example.citysearchapp.repository

import com.example.citysearchapp.data.IFileCityLoader
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.repository.interfaces.ICityLoaderRepository
import com.example.citysearchapp.utils.mapper.mapToDomainCity

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class CityLoaderRepository constructor(private val fileCityLoader: IFileCityLoader) :
    ICityLoaderRepository {

    override suspend fun load(): List<City> {
        return fileCityLoader.load().mapToDomainCity()
    }

}