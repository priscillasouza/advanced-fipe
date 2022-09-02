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
        consultViewModel = ConsultViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        consultViewModel.getBrands(type)
        setHasOptionsMenu(true)
        setNavigationIcon()
        setClickButtonConsult()
        onObserver()
    }

    private fun setNavigationIcon() {
        binding.toolBarConsultScreen.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolBarConsultScreen.setNavigationOnClickListener {
            navController.popBackStack()
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
                Toast.makeText(requireContext(), "Falha na lista de marcas", Toast.LENGTH_SHORT)
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
                Toast.makeText(requireContext(), "Falha na lista de modelos", Toast.LENGTH_SHORT)
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
                Toast.makeText(requireContext(), "Falha na lista de ano modelo", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        consultViewModel.apply {
            resultVehicle.observe(viewLifecycleOwner) {
                setClickButtonConsult()
            }

            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Falha ao fazer a consulta", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setClickButtonConsult() {
        binding.buttonConsult.setOnClickListener {
            if (validateSelectedFields()) {
                val action =
                    ConsultFragmentDirections.actionConsultFragmentToResultFragment()
                findNavController().navigate(
                    action
                )
            } else {
                Toast.makeText(context, "Selecione todos os campos", Toast.LENGTH_SHORT).show()
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