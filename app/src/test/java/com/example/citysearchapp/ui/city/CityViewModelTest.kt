package com.example.citysearchapp.ui.city

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citysearchapp.TestDispatcherProvider
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.Coordinate
import com.example.citysearchapp.repository.interfaces.CityLoaderRepository
import com.example.citysearchapp.repository.interfaces.CityRepository
import com.example.citysearchapp.ui.event.CityEvent
import com.example.citysearchapp.usecase.GetAllCityUseCase
import com.example.citysearchapp.usecase.InsertCityUseCase
import com.example.citysearchapp.usecase.LoadCitiesUseCase
import com.example.citysearchapp.usecase.SearchCityUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

@ExperimentalCoroutinesApi
class CityViewModelTest {
    private lateinit var cityLoaderRepository: CityLoaderRepository
    private lateinit var cityRepository: CityRepository

    private lateinit var loadCityUseCase: LoadCitiesUseCase
    private lateinit var getAllCityUseCase: GetAllCityUseCase
    private lateinit var insertCityUseCase: InsertCityUseCase
    private lateinit var searchCityUseCase: SearchCityUseCase

    private lateinit var cityViewModel: CityViewModel

    @ExperimentalCoroutinesApi
    val appDispatcher = TestDispatcherProvider()

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        cityLoaderRepository = mockk(relaxUnitFun = true)
        cityRepository = mockk(relaxUnitFun = true)

        loadCityUseCase = LoadCitiesUseCase(cityLoaderRepository)
        getAllCityUseCase = GetAllCityUseCase(cityRepository)
        insertCityUseCase = InsertCityUseCase(cityRepository)
        searchCityUseCase = SearchCityUseCase(cityRepository)

        cityViewModel = CityViewModel(
            loadCityUseCase,
            insertCityUseCase,
            getAllCityUseCase,
            searchCityUseCase,
            appDispatcher
        )
    }

    @Test
    fun `load city on start view model must succeed`(): Unit = appDispatcher.run {


        val data = arrayListOf(
            City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
        )

        every {
            runBlocking {
                cityLoaderRepository.load()
            }
        }.returns(data)

        every {
            runBlocking {
                cityRepository.insert(any())
            }
        }.returns(true)

        every {
            runBlocking {
                cityRepository.getAll()
            }
        }.returns(data)

        cityViewModel.onStart()

        verify(exactly = 1) {
            runBlocking {
                cityLoaderRepository.load()
            }
        }

        verify(exactly = 1) {
            runBlocking {
                cityRepository.insert(data)
            }
        }

        verify(exactly = 1) {
            runBlocking {
                cityRepository.getAll()
            }
        }

        assertThat(cityViewModel.state.value?.listCity?.size).isEqualTo(2)
        assertThat(cityViewModel.state.value?.loading).isFalse()
        assertThat(cityViewModel.state.value?.emptyView).isFalse()
        assertThat(cityViewModel.state.value?.retry).isFalse()

    }

    @Test
    fun `search city must update state succeed`(): Unit = appDispatcher.run {

        every {
            runBlocking {
                cityRepository.search(any())
            }
        }.returns(
            arrayListOf(
                City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            )
        )

        cityViewModel.event(CityEvent.Search("IN"))

        verify(exactly = 1) {
            runBlocking {
                cityRepository.search("IN")
            }
        }


        assertThat(cityViewModel.state.value?.listCity?.size).isEqualTo(1)
        assertThat(cityViewModel.state.value?.listCity?.get(0)).isEqualTo(
            City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
        )
        assertThat(cityViewModel.state.value?.loading).isFalse()
        assertThat(cityViewModel.state.value?.emptyView).isFalse()
        assertThat(cityViewModel.state.value?.retry).isFalse()
    }
}