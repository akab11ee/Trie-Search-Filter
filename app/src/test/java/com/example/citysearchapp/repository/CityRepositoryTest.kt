package com.example.citysearchapp.repository

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.Coordinate
import com.example.citysearchapp.data.trie.Trie
import com.example.citysearchapp.repository.interfaces.ICityRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class CityRepositoryTest {
    private lateinit var trie: Trie<City>
    private lateinit var cityRepository: ICityRepository


    @Before
    fun setUp() {
        trie = Mockito.mock(Trie::class.java) as Trie<City>
        cityRepository = CityRepository(trie)
    }

    @Test
    fun `insert city list must return true`(): Unit = runBlocking {
        Mockito.`when`(trie.insert(any())).thenReturn(true)

        val result = cityRepository.insert(
            arrayListOf(
                City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
                City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
            )
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `search city in must return not empty`(): Unit = runBlocking {
        Mockito.`when`(trie.search("U")).thenReturn(
            arrayListOf(
                City("USA", "New York", 42, Coordinate(44.549999, 34.283333))
            )
        )
        assertThat(cityRepository.search("U")).isEqualTo(
            arrayListOf(City("USA", "New York", 42, Coordinate(44.549999, 34.283333)))
        )
    }

    @Test
    fun `get all cities must return not empty`(): Unit = runBlocking {
        Mockito.`when`(trie.all()).thenReturn(
            arrayListOf(
                City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
                City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
            )
        )

        assertThat(cityRepository.getAll()).isEqualTo(
            arrayListOf(
                City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
                City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
            )
        )
    }

    @Test
    fun `clear cities must succeed`(): Unit = runBlocking {
        doNothing().`when`(trie).clear()

        cityRepository.clear()

        verify(trie, times(1)).clear()
    }

}