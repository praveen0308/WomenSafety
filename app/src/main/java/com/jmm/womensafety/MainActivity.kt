package com.jmm.womensafety

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.jmm.womensafety.databinding.ActivityMainBinding
import com.jmm.womensafety.util.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = Navigation.findNavController(this,R.id.nav_host_main)
        NavigationUI.setupWithNavController(binding.bottomNav,navController)

    }

    override fun subscribeObservers() {

    }
}