package com.example.citysearchapp.repository.interfaces

import com.example.citysearchapp.data.entity.City

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

interface CityLoaderRepository {
    /**
     * Load data from test data set
     */
    suspend fun load(): List<City>
}