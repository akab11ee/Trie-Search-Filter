package com.example.citysearchapp.usecase

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.Coordinate
import com.example.citysearchapp.repository.interfaces.ICityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class InsertCityUseCaseTest {
    private lateinit var insertCityUseCase: InsertCityUseCase
    private lateinit var cityRepository: ICityRepository

    @Before
    fun setUp() {
        cityRepository = Mockito.mock(ICityRepository::class.java)
        insertCityUseCase = InsertCityUseCase(cityRepository)
    }

    @Test
    fun `insert city list must call repository insert succeed`(): Unit = runBlocking {
        insertCityUseCase(
            arrayListOf(
                City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
                City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
            )
        )
        verify(cityRepository, times(1)).insert(any())
    }
}