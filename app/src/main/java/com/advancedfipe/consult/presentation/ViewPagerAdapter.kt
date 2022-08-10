package com.advancedfipe.consult.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragment: ConsultFragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ConsultCommonFragment()
            }
            1 -> {
                ConsultCodeFragment()
            }
            else -> {
                ConsultCommonFragment()
            }
        }
    }
}