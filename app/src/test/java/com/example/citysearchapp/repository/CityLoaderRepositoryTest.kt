package com.example.citysearchapp.repository

import com.example.citysearchapp.data.FileCityLoader
import com.example.citysearchapp.data.entity.CityEntity
import com.example.citysearchapp.data.entity.CoordinateEntity
import com.example.citysearchapp.repository.interfaces.CityLoaderRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class CityLoaderRepositoryTest {

    private lateinit var fileCityLoader: FileCityLoader
    private lateinit var cityLoaderRepository: CityLoaderRepository


    @Before
    fun setUp() {
        fileCityLoader = mockk(relaxUnitFun = true)
        every { fileCityLoader.load() }.returns(
            arrayListOf(
                CityEntity("IN", "Chandigarh", 25, CoordinateEntity(44.549999, 34.283333)),
                CityEntity("IN", "Stuttgart", 19, CoordinateEntity(44.549999, 34.283333)),
                CityEntity("USA", "New York", 42, CoordinateEntity(44.549999, 34.283333)),
                CityEntity("SW", "Stockholm", 23, CoordinateEntity(44.549999, 34.283333)),
            )
        )

        cityLoaderRepository = CityLoaderRepositoryImpl(fileCityLoader)
    }

    @Test
    fun `load city from file must not empty`(): Unit = runBlocking {
        assertThat(cityLoaderRepository.load()).isNotEmpty()
    }

    @Test
    fun `load city entity from file and map to city must succeed`(): Unit = runBlocking {
        val cityEntity = fileCityLoader.load()
        val city = cityLoaderRepository.load()

        assertThat(cityEntity.size).isEqualTo(city.size)

        for (index in cityEntity.indices) {
            assertThat(cityEntity[index].country).isEqualTo(city[index].country)
            assertThat(cityEntity[index].name).isEqualTo(city[index].name)
            assertThat(cityEntity[index].id).isEqualTo(city[index].id)
        }
    }
}