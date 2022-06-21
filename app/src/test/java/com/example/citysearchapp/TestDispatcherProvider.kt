package com.example.citysearchapp

import com.example.citysearchapp.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

@ExperimentalCoroutinesApi
class TestDispatcherProvider : DispatcherProvider {

    private val testDispatcher = TestCoroutineDispatcher()

    override fun comp(): CoroutineDispatcher {
        return testDispatcher
    }

    override fun io(): CoroutineDispatcher {

        return testDispatcher
    }


    override fun main(): CoroutineDispatcher {
        return testDispatcher
    }
}