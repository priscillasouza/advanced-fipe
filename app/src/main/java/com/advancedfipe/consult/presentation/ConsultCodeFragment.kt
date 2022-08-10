package com.advancedfipe.consult.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.advancedfipe.databinding.FragmentConsultCodeScreenBinding

class ConsultCodeFragment : Fragment() {

    private lateinit var binding: FragmentConsultCodeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConsultCodeScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}