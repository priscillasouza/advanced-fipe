package com.advancedfipe.consult.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.advancedfipe.databinding.FragmentOptionsConsultBinding

class OptionsConsultFragment : Fragment() {

    private lateinit var binding: FragmentOptionsConsultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOptionsConsultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOptionsCardsClicks()
    }

    private fun setOptionsCardsClicks() {
        binding.apply {
            cardViewCar.setOnClickListener {
                nextPage(
                    OptionsConsultFragmentDirections.actionOptionsConsultFragmentToConsultFragment()
                )
            }
            binding.cardViewMotorcycle.setOnClickListener {
                nextPage(
                    OptionsConsultFragmentDirections.actionOptionsConsultFragmentToConsultFragment()
                )
            }
            binding.cardViewTruck.setOnClickListener {
                nextPage(
                    OptionsConsultFragmentDirections.actionOptionsConsultFragmentToConsultFragment()
                )
            }
        }
    }

    private fun nextPage(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}