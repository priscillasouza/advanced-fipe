package com.advancedfipe.consult.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.advancedfipe.R
import com.advancedfipe.databinding.FragmentGraphicBinding

class GraphicFragment: Fragment() {

    private lateinit var binding: FragmentGraphicBinding
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGraphicBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setNavigationIcon()
    }

    private fun setNavigationIcon() {
        binding.toolBarGraphic.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolBarGraphic.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }
}