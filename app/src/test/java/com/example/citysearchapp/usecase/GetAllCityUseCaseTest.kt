package com.example.citysearchapp.usecase

import com.example.citysearchapp.repository.interfaces.ICityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times

class GetAllCityUseCaseTest {

    private lateinit var getAllCityUseCase: GetAllCityUseCase
    private lateinit var cityRepository: ICityRepository


    @Before
    fun setUp() {
        cityRepository = Mockito.mock(ICityRepository::class.java)
        getAllCityUseCase = GetAllCityUseCase(cityRepository)
    }

    @Test
    fun `get all must call repository get all succeed`(): Unit = runBlocking {
        getAllCityUseCase()
        Mockito.verify(cityRepository, times(1)).getAll()
    }
}