package com.advancedfipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.advancedfipe.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationController()
    }

    private fun setNavigationController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigation = findViewById(R.id.bottom_navigation)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.optionsConsultFragment -> {
                    showBottomNav()
                }
                R.id.favoritesFragment -> {
                    showBottomNav()
                }
                else -> {
                    hideBottomNav()
                }
            }
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.optionsConsultFragment,
                R.id.favoritesFragment
            )
        )
        bottomNavigation.setupWithNavController(navController)
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }
}