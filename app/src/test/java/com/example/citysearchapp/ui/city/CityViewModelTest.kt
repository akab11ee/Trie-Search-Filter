package com.example.citysearchapp.ui.city

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citysearchapp.CoroutineDispatchersRule
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.Coordinate
import com.example.citysearchapp.repository.interfaces.ICityLoaderRepository
import com.example.citysearchapp.repository.interfaces.ICityRepository
import com.example.citysearchapp.ui.event.CityEvent
import com.example.citysearchapp.usecase.GetAllCityUseCase
import com.example.citysearchapp.usecase.InsertCityUseCase
import com.example.citysearchapp.usecase.LoadCitiesUseCase
import com.example.citysearchapp.usecase.SearchCityUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

@ExperimentalCoroutinesApi
class CityViewModelTest {
    private lateinit var cityLoaderRepository: ICityLoaderRepository
    private lateinit var cityRepository: ICityRepository

    private lateinit var loadCityUseCase: LoadCitiesUseCase
    private lateinit var getAllCityUseCase: GetAllCityUseCase
    private lateinit var insertCityUseCase: InsertCityUseCase
    private lateinit var searchCityUseCase: SearchCityUseCase

    private lateinit var cityViewModel: CityViewModel

    @get:Rule
    val coroutineRule = CoroutineDispatchersRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        cityLoaderRepository = Mockito.mock(ICityLoaderRepository::class.java)
        cityRepository = Mockito.mock(ICityRepository::class.java)

        loadCityUseCase = LoadCitiesUseCase(cityLoaderRepository)
        getAllCityUseCase = GetAllCityUseCase(cityRepository)
        insertCityUseCase = InsertCityUseCase(cityRepository)
        searchCityUseCase = SearchCityUseCase(cityRepository)

        cityViewModel = CityViewModel(
            loadCityUseCase,
            insertCityUseCase,
            getAllCityUseCase,
            searchCityUseCase,
            coroutineRule.dispatcher
        )
    }

    @Test
    fun `load city on start view model must succeed`(): Unit = coroutineRule.runBlockingTest {

        val data = arrayListOf(
            City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
        )

        Mockito.`when`(cityLoaderRepository.load()).thenReturn(data)

        Mockito.`when`(cityRepository.insert(any())).thenReturn(true)

        Mockito.`when`(cityRepository.getAll()).thenReturn(data)

        cityViewModel.onStart()

        verify(cityLoaderRepository, times(1)).load()
        verify(cityRepository, times(1)).insert(data)
        verify(cityRepository, times(1)).getAll()

        assertThat(cityViewModel.state.value?.listCity?.size).isEqualTo(2)
        assertThat(cityViewModel.state.value?.loading).isFalse()
        assertThat(cityViewModel.state.value?.emptyView).isFalse()
        assertThat(cityViewModel.state.value?.retry).isFalse()
    }

    @Test
    fun `search city must update state succeed`(): Unit = coroutineRule.runBlockingTest {

        Mockito.`when`(cityRepository.search(any())).thenReturn(
            arrayListOf(
                City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            )
        )

        cityViewModel.event(CityEvent.Search("IN"))

        verify(cityRepository, times(1)).search("IN")

        assertThat(cityViewModel.state.value?.listCity?.size).isEqualTo(1)
        assertThat(cityViewModel.state.value?.listCity?.get(0)).isEqualTo(
            City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
        )
        assertThat(cityViewModel.state.value?.loading).isFalse()
        assertThat(cityViewModel.state.value?.emptyView).isFalse()
        assertThat(cityViewModel.state.value?.retry).isFalse()
    }
}