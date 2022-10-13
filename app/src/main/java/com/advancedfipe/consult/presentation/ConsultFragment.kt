package com.advancedfipe.consult.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.advancedfipe.R
import com.advancedfipe.consult.adapter.AdapterBaseVehicleFilter
import com.advancedfipe.consult.domain.model.BaseVehicleFilter
import com.advancedfipe.consult.domain.model.Brand
import com.advancedfipe.consult.domain.model.Model
import com.advancedfipe.consult.domain.model.ModelYear
import com.advancedfipe.consult.viewmodel.ConsultViewModel
import com.advancedfipe.databinding.FragmentConsultBinding
import com.advancedfipe.extensions.show

class ConsultFragment : Fragment() {

    private lateinit var binding: FragmentConsultBinding
    private lateinit var consultViewModel: ConsultViewModel
    private val navController by lazy { findNavController() }
    private val args by navArgs<ConsultFragmentArgs>()
    private val type by lazy { args.type }
    private var brandSelected: Brand? = null
    private var modelSelected: Model? = null
    private var modelYearSelected: ModelYear? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentConsultBinding.inflate(layoutInflater, container, false)
        consultViewModel = ConsultViewModel(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        consultViewModel.getBrands(type)
        setNavigationIcon()
        setClickButtonConsult()
        onObserver()
    }

    private fun setNavigationIcon() {
        binding.apply {
            toolBarConsult.setNavigationIcon(R.drawable.ic_arrow_back)
            toolBarConsult.setNavigationOnClickListener {
                navController.popBackStack()
            }
        }
    }

    private fun onObserver() {
        consultViewModel.apply {
            brands.observe(viewLifecycleOwner) { brands ->
                setAdapterBaseVehicleFilter(
                    binding.autoCompleteTextViewBrand,
                    onItemClickListener = { adapter, _, position, _ ->
                        val brand = adapter.getItemAtPosition(position) as Brand
                        brandSelected = brand
                        binding.autoCompleteTextViewBrand.setText(brandSelected?.name)
                        consultViewModel.getModels(type, brand.code)
                    },
                    brands
                )
            }

            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(),
                    getString(R.string.text_brand_list_failure),
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        consultViewModel.apply {
            models.observe(viewLifecycleOwner) { models ->
                setAdapterBaseVehicleFilter(
                    binding.autoCompleteTextViewModel,
                    onItemClickListener = { adapter, _, position, _ ->
                        val model = adapter.getItemAtPosition(position) as Model
                        modelSelected = model
                        binding.autoCompleteTextViewModel.setText(modelSelected?.name)
                        consultViewModel.getModelYears(type,
                            brand = brandSelected?.code.orEmpty(),
                            model.code)
                    },
                    models
                )
            }

            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(),
                    getString(R.string.text_fail_in_models_list),
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        consultViewModel.apply {
            modelYears.observe(viewLifecycleOwner) { modelYears ->
                setAdapterBaseVehicleFilter(
                    binding.autoCompleteTextViewYear,
                    onItemClickListener = { adapter, _, position, _ ->
                        val modelYear = adapter.getItemAtPosition(position) as ModelYear
                        modelYearSelected = modelYear
                        binding.autoCompleteTextViewYear.setText(modelYearSelected?.name)
                        consultViewModel.getVehicle(type,
                            brand = brandSelected?.code.orEmpty(),
                            model = modelSelected?.code.orEmpty(),
                            modelYear.code)
                    },
                    modelYears
                )
            }

            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(),
                    getString(R.string.text_fail_in_model_year_list),
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        consultViewModel.apply {
            consultVehicle.observe(viewLifecycleOwner) {
                val action =
                    ConsultFragmentDirections.actionConsultFragmentToResultFragment(it)
                findNavController().navigate(
                    action
                )
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(),
                    getString(R.string.failed_making_consult),
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        consultViewModel.showProgressBar.observe(viewLifecycleOwner) { showProgressBar ->
            binding.progressBar.show(showProgressBar)
        }
    }

    private fun setClickButtonConsult() {
        binding.buttonConsult.setOnClickListener {
            if (validateSelectedFields()) {
                consultViewModel.getVehicle(type,
                    brandSelected?.code.orEmpty(),
                    modelSelected?.code.orEmpty(),
                    modelYearSelected?.code.orEmpty())
            } else {
                Toast.makeText(context,
                    getString(R.string.text_select_all_fields),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateSelectedFields(): Boolean {
        return (binding.autoCompleteTextViewBrand.text.isNotEmpty() && binding.autoCompleteTextViewModel.text.isNotEmpty() && binding.autoCompleteTextViewYear.text.isNotEmpty())
    }

    private fun setAdapterBaseVehicleFilter(
        autoCompleteTextView: AutoCompleteTextView,
        onItemClickListener: AdapterView.OnItemClickListener,
        brands: List<BaseVehicleFilter>,
    ) {
        val adapter = AdapterBaseVehicleFilter(requireContext(), brands)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        autoCompleteTextView.apply {
            setOnItemClickListener(onItemClickListener)
        }
        autoCompleteTextView.setAdapter(adapter)
    }
}