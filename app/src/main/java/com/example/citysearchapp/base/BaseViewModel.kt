package com.example.citysearchapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citysearchapp.utils.SOMETHING_WENT_WRONG
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

open class BaseViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    private val _showErrorToast = MutableLiveData<String>()
    val showErrorToast: LiveData<String> = _showErrorToast

    protected val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, _ ->
            showErrorToastToUser(SOMETHING_WENT_WRONG)
        }

    private fun showErrorToastToUser(error: String) {
        _showErrorToast.value = error
    }

    open fun getAppDispatcher(): CoroutineDispatcher {
        return dispatcher
    }
}