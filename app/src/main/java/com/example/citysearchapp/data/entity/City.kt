package com.example.citysearchapp.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.example.citysearchapp.data.trie.TrieModelWithKey

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 * @property name will be used for sorting the list
 */

data class City(
    val country: String,
    val name: String,
    val id: Int,
    val coordinate: Coordinate
) : TrieModelWithKey(name)

data class Coordinate(
    val lon: Double,
    val lat: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lon)
        parcel.writeDouble(lat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coordinate> {
        override fun createFromParcel(parcel: Parcel): Coordinate {
            return Coordinate(parcel)
        }

        override fun newArray(size: Int): Array<Coordinate?> {
            return arrayOfNulls(size)
        }
    }
}