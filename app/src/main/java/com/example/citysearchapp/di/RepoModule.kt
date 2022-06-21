package com.example.citysearchapp.di

import com.example.citysearchapp.data.FileCityLoaderImpl
import com.example.citysearchapp.data.FileCityLoader
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.trie.DefaultTrie
import com.example.citysearchapp.data.trie.Trie
import com.example.citysearchapp.repository.CityLoaderRepositoryImpl
import com.example.citysearchapp.repository.CityRepositoryImpl
import com.example.citysearchapp.repository.interfaces.CityLoaderRepository
import com.example.citysearchapp.repository.interfaces.CityRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

val repoModule = module {
    single<FileCityLoader> {
        FileCityLoaderImpl(androidContext(), get())
    }

    single<Trie<City>> {
        DefaultTrie()
    }

    single<CityLoaderRepository> {
        CityLoaderRepositoryImpl(get())
    }
    single<CityRepository> {
        CityRepositoryImpl(get())
    }
}
