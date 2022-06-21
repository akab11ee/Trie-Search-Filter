package com.example.citysearchapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.citysearchapp.utils.toastL


/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

abstract class BaseActivity<VB : ViewBinding> :
    AppCompatActivity() {

    abstract fun getViewBinding(): VB
    private var _binding: ViewBinding? = null
    private val binding: VB
        get() = _binding as VB

    //Initialize UI and listeners
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        initializeUI()
        setListener()
    }

    private fun showErrorToastToUser(errorDescription: String) {
        toastL(errorDescription)
    }

    protected open fun setListener() {}
    protected open fun initializeUI() {}

}