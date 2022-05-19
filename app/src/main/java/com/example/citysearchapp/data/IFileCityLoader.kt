package com.example.citysearchapp.data

import com.example.citysearchapp.data.entity.CityEntity


/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

interface IFileCityLoader {

    /**
     * load city data from file
     */
    fun load(): List<CityEntity>
}