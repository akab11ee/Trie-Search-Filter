package com.example.citysearchapp

import android.app.Application
import com.example.citysearchapp.di.appModule
import com.example.citysearchapp.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class CitySearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CitySearchApplication)
            androidLogger()
            modules(listOf(appModule, repoModule))
        }
    }
}