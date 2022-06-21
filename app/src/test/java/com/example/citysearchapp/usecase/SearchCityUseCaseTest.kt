package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.Coordinate
import com.example.citysearchapp.repository.interfaces.CityRepository
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

class SearchCityUseCaseTest {

    private lateinit var searchCityUseCase: SearchCityUseCase
    private lateinit var cityRepository: CityRepository


    @Before
    fun setUp() {
        cityRepository = mockk(relaxUnitFun = true)
        searchCityUseCase = SearchCityUseCase(cityRepository)
    }

    @Test
    fun `search must call repository search succeed`(): Unit = runBlocking {

        val data = arrayListOf(
            City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
        )

        every {
            runBlocking {
                cityRepository.search("k")
            }
        } returns data

        searchCityUseCase.invoke("k")

        verify(exactly = 1) {
            runBlocking {
                cityRepository.search("k")
            }
        }
    }

}