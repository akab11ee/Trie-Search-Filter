package com.example.citysearchapp.repository

import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.trie.Trie
import com.example.citysearchapp.repository.interfaces.ICityRepository

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class CityRepository constructor(private val trie: Trie<City>) : ICityRepository {
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