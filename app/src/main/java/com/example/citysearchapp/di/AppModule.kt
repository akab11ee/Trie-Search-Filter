package com.example.citysearchapp.di

import com.example.citysearchapp.data.FileCityLoaderImpl
import com.example.citysearchapp.ui.city.CityViewModel
import com.example.citysearchapp.usecase.GetAllCityUseCase
import com.example.citysearchapp.usecase.InsertCityUseCase
import com.example.citysearchapp.usecase.LoadCitiesUseCase
import com.example.citysearchapp.usecase.SearchCityUseCase
import com.example.citysearchapp.utils.coroutines.AppDispatcher
import com.example.citysearchapp.utils.coroutines.DispatcherProvider
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

val appModule = module {

    single {
        Gson()
    }

    single {
        FileCityLoaderImpl(androidContext(), get())
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

    single<DispatcherProvider> {
        AppDispatcher()
    }


    viewModel {
        CityViewModel(get(), get(), get(), get(), get())
    }
}