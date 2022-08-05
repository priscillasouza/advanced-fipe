package com.advancedfipe.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.advancedfipe.databinding.FragmentSearchOptionsScreenBinding

class OptionsConsultFragment : Fragment() {

    private lateinit var binding: FragmentSearchOptionsScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchOptionsScreenBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchOptionsCardsClicks()
    }

    private fun setSearchOptionsCardsClicks() {
        binding.apply {
            cardViewCar.setOnClickListener {
                //TODO navegar para o FragmentConsult
            }
            cardViewMotocycle.setOnClickListener {
                //TODO navegar para o FragmentConsult
            }
            cardViewTruck.setOnClickListener {
                //TODO navegar para o FragmentConsult
            }
        }
    }
}