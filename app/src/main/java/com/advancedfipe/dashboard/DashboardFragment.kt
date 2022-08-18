package com.advancedfipe.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.advancedfipe.R
import com.advancedfipe.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        setNavigationButton()
        return binding.root
    }

    private fun setNavigationButton() {
        binding.dashboardBottomNavigation.apply {
            val navHostFragment =
                childFragmentManager.findFragmentById(R.id.dashboard_fragment_container_view) as NavHostFragment
            val navController = navHostFragment.navController

            setupWithNavController(navController)
        }
    }
}



