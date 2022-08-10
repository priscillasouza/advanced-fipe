package com.advancedfipe.consult.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.advancedfipe.R
import com.advancedfipe.databinding.FragmentConsultScreenBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_consult_screen.*

class ConsultFragment : Fragment() {

    private lateinit var binding: FragmentConsultScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConsultScreenBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationIcon()
        setViewPager()
    }

    private fun setNavigationIcon() {
        tool_bar_consult_screen.setNavigationIcon(R.drawable.ic_arrow_back)
        tool_bar_consult_screen.setNavigationOnClickListener {
            findNavController().navigate(
                ConsultFragmentDirections.actionConsultFragmentToOptionsConsultFragment()
            )
        }
    }

    private fun setViewPager() {
        val tabTitle = arrayOf(
            getString(R.string.text_title_tab_commun_view_pager),
            getString(R.string.text_title_tab_code_view_pager)
        )
        binding.viewPagerConsult.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayoutConsult, binding.viewPagerConsult) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}