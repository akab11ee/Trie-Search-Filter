package com.example.citysearchapp.repository

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.entity.Coordinate
import com.example.citysearchapp.data.trie.Trie
import com.example.citysearchapp.repository.interfaces.CityRepository
import com.google.common.truth.Truth.assertThat
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

class CityRepositoryTest {
    private lateinit var trie: Trie<City>
    private lateinit var cityRepository: CityRepository


    @Before
    fun setUp() {
        trie = mockk(relaxUnitFun = true)
        cityRepository = CityRepositoryImpl(trie)
    }

    @Test
    fun `insert city list must return true`(): Unit = runBlocking {
        every { (trie.insert(any())) }.returns(true)

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
        every { (trie.search("U")) }.returns(
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
        every { (trie.all()) }.returns(
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
        every { trie.clear() } returns Unit

        cityRepository.clear()

        verify(exactly = 1) { trie.clear() }
    }

}