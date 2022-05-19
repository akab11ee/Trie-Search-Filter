package com.example.citysearchapp.data

import android.content.Context
import com.example.citysearchapp.R
import com.example.citysearchapp.data.entity.CityEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 * Method will convert json list of city from raw folder to CityEntity pojo class
 */

class FileCityLoader constructor(
    private val context: Context,
    private val gson: Gson
) : IFileCityLoader {

    override fun load(): List<CityEntity> {
        return try {
            context.resources.openRawResource(R.raw.cities).use {
                gson.fromJson(
                    it.bufferedReader().readText(),
                    object : TypeToken<List<CityEntity>>() {}.type
                ) as List<CityEntity>
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }
}