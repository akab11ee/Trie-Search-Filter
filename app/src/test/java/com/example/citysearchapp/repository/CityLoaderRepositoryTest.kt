package com.example.citysearchapp.repository

import com.example.citysearchapp.data.IFileCityLoader
import com.example.citysearchapp.data.entity.CityEntity
import com.example.citysearchapp.data.entity.CoordinateEntity
import com.example.citysearchapp.repository.interfaces.ICityLoaderRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class CityLoaderRepositoryTest {

    private lateinit var fileCityLoader: IFileCityLoader
    private lateinit var cityLoaderRepository: ICityLoaderRepository


    @Before
    fun setUp() {
        fileCityLoader = Mockito.mock(IFileCityLoader::class.java)
        Mockito.`when`(fileCityLoader.load()).then {
            arrayListOf(
                CityEntity("IN", "Chandigarh", 25, CoordinateEntity(44.549999, 34.283333)),
                CityEntity("IN", "Stuttgart", 19, CoordinateEntity(44.549999, 34.283333)),
                CityEntity("USA", "New York", 42, CoordinateEntity(44.549999, 34.283333)),
                CityEntity("SW", "Stockholm", 23, CoordinateEntity(44.549999, 34.283333)),
            )
        }
        cityLoaderRepository = CityLoaderRepository(fileCityLoader)
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