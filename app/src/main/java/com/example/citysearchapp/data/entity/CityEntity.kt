package com.example.citysearchapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

data class CityEntity(
    @SerializedName("country") var country: String,
    @SerializedName("name") var name: String,
    @SerializedName("_id") var id: Int,
    @SerializedName("coord") var coord: CoordinateEntity
)


data class CoordinateEntity(
    @SerializedName("lon") var lon: Double,
    @SerializedName("lat") var lat: Double
)
