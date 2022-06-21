package com.example.citysearchapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citysearchapp.utils.SOMETHING_WENT_WRONG
import com.example.citysearchapp.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

open class BaseViewModel(private val dispatcher: DispatcherProvider) : ViewModel() {

    private val _showErrorToast = MutableLiveData<String>()
    val showErrorToast: LiveData<String> = _showErrorToast

    protected val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            showErrorToastToUser(SOMETHING_WENT_WRONG)
        }

    private fun showErrorToastToUser(error: String) {
        _showErrorToast.value = error
    }

    open fun getAppDispatcher(): DispatcherProvider {
        return dispatcher
    }
}