package com.example.citysearchapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment(),
    LifecycleObserver {

    abstract val viewModel: VM

    abstract fun getViewBinding(): VB
    private var _binding: ViewBinding? = null
    protected val binding: VB
        get() = _binding as VB

    private var isInitialized = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        loadData()
        observeData()
        if (!isInitialized) {
            isInitialized = true
            setupErrorToast()
        }
    }

    protected open fun initUI() {}
    protected open fun loadData() {}
    protected open fun observeData() {}

    private fun setupErrorToast() {
        viewModel.showErrorToast.observe(requireActivity()) { error ->
            showErrorToastToUser(error)
        }
    }

    private fun showErrorToastToUser(errorDescription: String?) {
        Toast.makeText(requireActivity(), errorDescription, Toast.LENGTH_SHORT).show()
    }
}