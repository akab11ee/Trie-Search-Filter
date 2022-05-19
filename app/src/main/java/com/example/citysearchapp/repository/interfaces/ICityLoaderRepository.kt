package com.example.citysearchapp.repository.interfaces

import com.example.citysearchapp.data.entity.City

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

interface ICityLoaderRepository {
    /**
     * Load data from test data set
     */
    suspend fun load(): List<City>
}