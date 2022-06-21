package com.example.citysearchapp.data

import com.example.citysearchapp.data.entity.CityEntity


/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

interface FileCityLoader {

    /**
     * load city data from file
     */
    fun load(): List<CityEntity>
}