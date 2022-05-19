package com.example.citysearchapp.di

import com.example.citysearchapp.data.FileCityLoader
import com.example.citysearchapp.data.IFileCityLoader
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.data.trie.DefaultTrie
import com.example.citysearchapp.data.trie.Trie
import com.example.citysearchapp.repository.CityLoaderRepository
import com.example.citysearchapp.repository.CityRepository
import com.example.citysearchapp.repository.interfaces.ICityLoaderRepository
import com.example.citysearchapp.repository.interfaces.ICityRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

val repoModule = module {
    single<IFileCityLoader> {
        FileCityLoader(androidContext(), get())
    }

    single<Trie<City>> {
        DefaultTrie()
    }

    single<ICityLoaderRepository> {
        CityLoaderRepository(get())
    }
    single<ICityRepository> {
        CityRepository(get())
    }
}
