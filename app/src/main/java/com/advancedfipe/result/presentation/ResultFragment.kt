package com.advancedfipe.result.presentation

import android.content.Intent
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
        setNavigationIconsToolBar()
        getResultConsult()
        setClickFavorite()
        setClickButtonGraphic()
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
                        setOptionToShare()
                        true
                    }
                    R.id.item_menu_favorites -> {
                        findNavController().navigate(
                            ResultFragmentDirections.actionResultFragmentToFavoritesFragment()
                        )
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
                    if (it.favorite) getString(R.string.text_favorited) else getString(R.string.text_disfavor),
                    Toast.LENGTH_SHORT)
                    .show()
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(),
                    getString(R.string.text_error_favorite),
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

    private fun setOptionToShare() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = getString(R.string.text_plain_type)
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_share_title))
        intent.putExtra(Intent.EXTRA_TEXT,
            String.format(getString(R.string.text_share),
                vehicle.referenceMonth,
                vehicle.fipeCode,
                vehicle.brand,
                vehicle.model,
                vehicle.modelYear,
                vehicle.price))
        startActivity(Intent.createChooser(intent, getString(R.string.text_share_options)))
    }

    private fun setClickButtonGraphic() {
        binding.buttonGraphic.setOnClickListener {
            findNavController().navigate(
                ResultFragmentDirections.actionResultFragmentToGraphicFragment()
            )
        }
    }
}