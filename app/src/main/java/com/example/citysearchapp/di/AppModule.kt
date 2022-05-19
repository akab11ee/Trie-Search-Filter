package com.example.citysearchapp.di

import com.example.citysearchapp.data.FileCityLoader
import com.example.citysearchapp.ui.city.CityViewModel
import com.example.citysearchapp.usecase.GetAllCityUseCase
import com.example.citysearchapp.usecase.InsertCityUseCase
import com.example.citysearchapp.usecase.LoadCitiesUseCase
import com.example.citysearchapp.usecase.SearchCityUseCase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

val appModule = module {

    single {
        Gson()
    }

    single {
        FileCityLoader(androidContext(), get())
    }

    single {
        LoadCitiesUseCase(get())
    }

    single {
        InsertCityUseCase(get())
    }

    single {
        GetAllCityUseCase(get())
    }
    single {
        SearchCityUseCase(get())
    }

    single<CoroutineDispatcher> {
        Dispatchers.Main
    }


    viewModel {
        CityViewModel(get(), get(), get(), get(), get())
    }
}