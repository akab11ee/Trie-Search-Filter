package com.example.citysearchapp.repository

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.trie.Trie
import com.example.citysearchapp.repository.interfaces.CityRepository

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class CityRepositoryImpl constructor(private val trie: Trie<City>) : CityRepository {
    override suspend fun insert(cityList: List<City>): Boolean {
        return trie.insert(cityList)
    }

    override suspend fun search(query: String): List<City> {
        return trie.search(query)
    }

    override suspend fun getAll(): List<City> {
        return trie.all()
    }

    override fun clear() {
        return trie.clear()
    }
}