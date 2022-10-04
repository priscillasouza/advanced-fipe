package com.advancedfipe.result.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.advancedfipe.R
import com.advancedfipe.databinding.FragmentResultBinding
import com.advancedfipe.result.viewmodel.ResultViewModel

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var resultViewModel: ResultViewModel
    private val navController by lazy { findNavController() }
    private val args by navArgs<ResultFragmentArgs>()
    private val vehicle by lazy { args.vehicle }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        resultViewModel = ResultViewModel(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserver()
        getResultConsult()
        setNavigationIconsToolBar()
        setClickButtonGraphic()
        setClickFavorite()
    }

    private fun setNavigationIconsToolBar() {
        binding.apply {
            toolBarResult.setNavigationIcon(R.drawable.ic_arrow_back)
            toolBarResult.setNavigationOnClickListener {
                navController.popBackStack()
            }
            toolBarResult.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.item_menu_share -> {
                        Toast.makeText(context, "Ícone para compartilhar", Toast.LENGTH_LONG)
                            .show()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun onObserver() {
        resultViewModel.apply {
            updateVehicle.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(),
                    if (it.favorite) "FAVORITADO" else "DESFAVORITADO",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(),
                    "Falha ao favoritar o veículo",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setClickFavorite() {
        binding.apply {
            iconCheckBoxFavorite.isChecked = vehicle.favorite
            iconCheckBoxFavorite.setOnCheckedChangeListener { _, isChecked ->
                resultViewModel.updateFavoriteVehicle(vehicle.copy(favorite = isChecked))
            }
        }
    }

    private fun getResultConsult() {
        binding.apply {
            textViewResultMonthReferences.text = vehicle.referenceMonth
            textViewResultCodeFipe.text = vehicle.fipeCode
            textViewResultBrand.text = vehicle.brand
            textViewResultModel.text = vehicle.model
            textViewResultModelYear.text = vehicle.modelYear.toString()
            textViewResultPrice.text = vehicle.price
        }
    }

    private fun setClickButtonGraphic() {
        binding.buttonGraphic.setOnClickListener {
            findNavController().navigate(
                ResultFragmentDirections.actionResultFragmentToGraphicFragment()
            )
        }
    }
}