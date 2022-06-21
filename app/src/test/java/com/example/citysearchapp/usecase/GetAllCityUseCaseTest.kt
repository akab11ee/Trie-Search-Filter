package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.Coordinate
import com.example.citysearchapp.repository.interfaces.CityRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class GetAllCityUseCaseTest {

    private lateinit var getAllCityUseCase: GetAllCityUseCase
    private lateinit var cityRepository: CityRepository


    @Before
    fun setUp() {
        cityRepository = mockk(relaxUnitFun = true)
        getAllCityUseCase = GetAllCityUseCase(cityRepository)
    }

    @Test
    fun `get all must call repository get all succeed`(): Unit = runBlocking {
        val data = arrayListOf(
            City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
        )

        every {
            runBlocking {
                cityRepository.getAll()
            }
        } returns data

        getAllCityUseCase.invoke()

        verify(exactly = 1) {
            runBlocking {
                cityRepository.getAll()
            }
        }
    }
}