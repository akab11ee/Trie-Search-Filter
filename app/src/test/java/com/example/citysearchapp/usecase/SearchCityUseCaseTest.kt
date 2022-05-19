package com.example.citysearchapp.usecase

import com.example.citysearchapp.repository.interfaces.ICityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class SearchCityUseCaseTest {

    private lateinit var searchCityUseCase: SearchCityUseCase
    private lateinit var cityRepository: ICityRepository


    @Before
    fun setUp() {
        cityRepository = Mockito.mock(ICityRepository::class.java)
        searchCityUseCase = SearchCityUseCase(cityRepository)
    }

    @Test
    fun `search must call repository search succeed`(): Unit = runBlocking {
        searchCityUseCase("k")
        verify(cityRepository, times(1)).search("k")
    }

}