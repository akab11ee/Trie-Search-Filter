package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.Coordinate
import com.example.citysearchapp.repository.interfaces.CityLoaderRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class LoadCitiesUseCaseTest {

    private lateinit var loadCityUseCase: LoadCitiesUseCase
    private lateinit var cityLoaderRepository: CityLoaderRepository


    @Before
    fun setUp() {
        cityLoaderRepository = mockk(relaxUnitFun = true)
        loadCityUseCase = LoadCitiesUseCase(cityLoaderRepository)
    }


    @Test
    fun `load cities from loader must call repository load succeed`(): Unit = runBlocking {

        val data = arrayListOf(
            City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
        )

        every {
            runBlocking {
                cityLoaderRepository.load()
            }
        } returns data

        loadCityUseCase.invoke()

        verify(exactly = 1) {
            runBlocking {
                cityLoaderRepository.load()
            }
        }
    }
}