package com.advancedfipe.consult.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.advancedfipe.databinding.FragmentConsultCommonScreenBinding

class ConsultCommonFragment : Fragment() {

    private lateinit var binding: FragmentConsultCommonScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConsultCommonScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}