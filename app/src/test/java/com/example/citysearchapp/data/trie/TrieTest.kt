package com.example.citysearchapp.data.trie

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class TrieTest {

    private val trie: Trie<City> = DefaultTrie()

    @Before
    fun setup() {
        trie.clear()
    }

    @Test
    fun `insert city list to structure return true`() {
        val list = produceTestCityList()
        assertThat(trie.insert(list)).isTrue()
    }

    @Test
    fun `insert empty list to structure return false`() {
        val list = emptyList<City>()
        assertThat(trie.insert(list)).isFalse()
    }

    @Test
    fun `insert duplicated city list to structure return true`() {
        val list = produceTestCityList()
        trie.insert(list)
        assertThat(trie.insert(list)).isTrue()
    }

    @Test
    fun `insert duplicated city list to structure dose not increase length`() {
        val list = produceTestCityList()
        val len = list.size
        trie.insert(list)
        trie.insert(list)
        assertThat(trie.length()).isEqualTo(len)
    }

    @Test
    fun `insert duplicated element in list must not increase structure size`() {
        val list = produceTestCityListWithDuplicated()
        trie.insert(list)
        assertThat(trie.length()).isEqualTo(list.size - 1)
    }

    @Test
    fun `search empty value return all data`() {
        val list = produceTestCityList()
        val len = list.size
        trie.insert(list)
        assertThat(trie.search("").size).isEqualTo(len)
    }

    @Test
    fun `search value that not exist in structure return empty list`() {
        val list = produceTestCityList()
        trie.insert(list)
        assertThat(trie.search("ABCDE").size).isEqualTo(0)
    }

    @Test
    fun `find all city that start with given prefix`() {
        val list = produceTestCityList()
        trie.insert(list)
        assertThat(trie.search("in").size).isEqualTo(2)
    }

    @Test
    fun `search return sorted list`() {
        val list = produceTestCityList()
        val sorted = list.sortedBy { it.key }
        trie.insert(list)
        assertThat(trie.search("")).isEqualTo(sorted)
    }

    @Test
    fun `get all return list of all data in structure`() {
        val list = produceTestCityList()
        trie.insert(list)
        assertThat(trie.all().size).isEqualTo(list.size)
    }

    @Test
    fun `get all data must return all data except duplicated`() {
        val list = produceTestCityListWithDuplicated()
        trie.insert(list)
        assertThat(trie.all().size).isEqualTo(5)
    }

    @Test
    fun `get all data sorted alphabetically`() {
        val list = produceTestCityList()
        val sorted = list.sortedBy { it.key }
        trie.insert(list)
        assertThat(trie.all()).isEqualTo(sorted)
    }

    @Test
    fun `clear must remove all data in structure`() {
        val list = produceTestCityList()
        trie.insert(list)
        trie.clear()
        assertThat(trie.all().size).isEqualTo(0)
    }

    private fun produceTestCityList(): List<City> {
        return listOf(
            City("IN", "Chandigarh", 25, Coordinate(44.549999, 34.283333)),
            City("IN", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
            City("SW", "Stockholm", 23, Coordinate(44.549999, 34.283333)),
        )
    }

    private fun produceTestCityListWithDuplicated(): List<City> {
        return listOf(
            City("DK", "Copenhagen", 33, Coordinate(44.549999, 34.283333)),
            City("DK", "Copenhagen", 33, Coordinate(44.549999, 34.283333)),
            City("IN", "Chandigarh", 25, Coordinate(44.549999, 34.283333)),
            City("GE", "Stuttgart", 19, Coordinate(44.549999, 34.283333)),
            City("USA", "New York", 42, Coordinate(44.549999, 34.283333)),
            City("SW", "Stockholm", 23, Coordinate(44.549999, 34.283333)),
        )
    }


    private data class City(
        val country: String,
        val name: String,
        val id: Int,
        val coordinate: Coordinate
    ) : TrieModelWithKey("$country$name")

    data class Coordinate(
        val lon: Double,
        val lat: Double
    )
}