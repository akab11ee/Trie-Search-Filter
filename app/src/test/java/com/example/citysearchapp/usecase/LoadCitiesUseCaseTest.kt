package com.example.citysearchapp.usecase

import com.example.citysearchapp.repository.interfaces.ICityLoaderRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class LoadCitiesUseCaseTest {

    private lateinit var loadCityUseCase: LoadCitiesUseCase
    private lateinit var cityLoaderRepository: ICityLoaderRepository


    @Before
    fun setUp() {
        cityLoaderRepository = Mockito.mock(ICityLoaderRepository::class.java)
        loadCityUseCase = LoadCitiesUseCase(cityLoaderRepository)
    }


    @Test
    fun `load cities from loader must call repository load succeed`(): Unit = runBlocking {
        loadCityUseCase()
        verify(cityLoaderRepository, times(1)).load()
    }
}