package com.example.citysearchapp.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.citysearchapp.R
import com.example.citysearchapp.base.BaseActivity
import com.example.citysearchapp.databinding.ActivityCityBinding
import kotlinx.android.synthetic.main.activity_city.*

/**
 * @Author: Akash Abhishek
 * @Date: 22 June 2022
 */

class CityActivity : BaseActivity<ActivityCityBinding>() {

    private lateinit var navController: NavController

    override fun getViewBinding(): ActivityCityBinding = ActivityCityBinding.inflate(layoutInflater)

    override fun setListener() {
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}