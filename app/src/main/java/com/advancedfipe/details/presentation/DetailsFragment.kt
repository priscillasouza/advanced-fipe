package com.advancedfipe.details.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.advancedfipe.R
import com.advancedfipe.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()
    private val vehicle by lazy { args.vehicle }
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationIconToolBar()
        getDetails()
    }

    private fun setNavigationIconToolBar() {
        binding.apply {
            toolBarDetails.setNavigationIcon(R.drawable.ic_arrow_back)
            toolBarDetails.setNavigationOnClickListener {
                navController.popBackStack()
            }
            toolBarDetails.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.item_menu_options_consult_help -> {
                        setOptionToShare()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun getDetails() {
        binding.apply {
            textViewResultMonthReferencesDetails.text = vehicle.referenceMonth
            textViewResultCodeFipeDetails.text = vehicle.fipeCode
            textViewResultBrandDetails.text = vehicle.brand
            textViewResultModelDetails.text = vehicle.model
            textViewResultModelYearDetails.text = vehicle.modelYear.toString()
            textViewResultPriceDetails.text = vehicle.price
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
}