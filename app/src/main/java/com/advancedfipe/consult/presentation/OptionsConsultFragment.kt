package com.advancedfipe.consult.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.advancedfipe.consult.presentation.ConstantsOptions.CAR
import com.advancedfipe.consult.presentation.ConstantsOptions.MOTORCYCLE
import com.advancedfipe.consult.presentation.ConstantsOptions.TRUCK
import com.advancedfipe.databinding.FragmentOptionsConsultBinding

class OptionsConsultFragment : Fragment() {

    private lateinit var binding: FragmentOptionsConsultBinding
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOptionsConsultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClicksOptionsCards()
    }

    private fun setClicksOptionsCards() {
        binding.apply {
            cardViewCar.setOnClickListener {
                goConsult(CAR)
            }
            cardViewMotorcycle.setOnClickListener {
                goConsult(MOTORCYCLE)
            }
            cardViewTruck.setOnClickListener {
                goConsult(TRUCK)
            }
        }
    }

    private fun goConsult(type: String) {
        val directions =
            OptionsConsultFragmentDirections.actionOptionsConsultFragmentToConsultFragment(type)
        navController.navigate(directions)
    }
}