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

class InsertCityUseCaseTest {
    private lateinit var insertCityUseCase: InsertCityUseCase
    private lateinit var cityRepository: CityRepository

    @Before
    fun setUp() {
        cityRepository = mockk(relaxUnitFun = true)
        insertCityUseCase = InsertCityUseCase(cityRepository)
    }

    @Test
    fun `insert city list must call repository insert succeed`(): Unit = runBlocking {

        every {
            runBlocking {
                cityRepository.insert(any())
            }
        } returns true

        insertCityUseCase.invoke(
            arrayListOf(
                City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
                City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
            )
        )


        verify(exactly = 1) {
            runBlocking {
                cityRepository.insert(any())
            }
        }
    }
}