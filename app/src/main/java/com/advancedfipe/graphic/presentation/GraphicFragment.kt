package com.advancedfipe.graphic.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.advancedfipe.R
import com.advancedfipe.consult.domain.model.Vehicle
import com.advancedfipe.databinding.FragmentGraphicBinding
import com.advancedfipe.extensions.removeMoney
import com.advancedfipe.graphic.viewmodel.GraphicViewModel
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.fragment_graphic.*

class GraphicFragment : Fragment() {

    private lateinit var binding: FragmentGraphicBinding
    private var graphicViewModel: GraphicViewModel? = null
    private val navController by lazy { findNavController() }
    private val args by navArgs<GraphicFragmentArgs>()
    private val vehicle by lazy { args.vehicle }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGraphicBinding.inflate(layoutInflater, container, false)
        graphicViewModel = activity?.baseContext?.run {
            GraphicViewModel(this)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationIconsToolBar()
        observerViewModel()
        graphicViewModel?.getVehicleById(vehicle.fipeCode)
        setStyle()
    }

    private fun setNavigationIconsToolBar() {
        binding.apply {
            toolBarGraphic.setNavigationIcon(R.drawable.ic_arrow_back)
            toolBarGraphic.setNavigationOnClickListener {
                navController.popBackStack()
            }
        }
    }

    private fun observerViewModel() {
        graphicViewModel?.apply {
            vehicle.observe(viewLifecycleOwner) {
                setValuesBarChart(it)
            }

            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(),
                    getString(R.string.text_error_displaying_chart_data),
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setValuesBarChart(values: List<Vehicle>) {
        val barEntries = ArrayList<BarEntry>()
        var i = 0

        for (entry in values) {
             val value = values[i].price
             barEntries.add(BarEntry(i.toFloat(), value.removeMoney().toFloat()))
             i++
         }

        val months = values.map {
            val index = it.referenceMonth.indexOf(" ")
            val word = it.referenceMonth.substring(0, index)
            word
        }

        val barDataSet = BarDataSet(barEntries, getString(R.string.text_price_bar_data_set))
        barDataSet.color = resources.getColor(R.color.blue)
        barDataSet.valueTextSize = 8f
        val data = BarData(barDataSet)

        binding.apply {
            barChartGraphic.data = data
            barChartGraphic.description = null
            barChartGraphic.animateXY(3000, 3000)
            barChartGraphic.xAxis.apply {
                setValueFormatter(object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return months[value.toInt() % months.size]
                    }
                })
            }
        }
    }

    fun setStyle() {
        val xAxis = bar_chart_graphic.xAxis
        xAxis.apply {
            textColor = R.color.black
            position = XAxisPosition.BOTTOM
            textSize = 12f
            setDrawGridLines(false)
        }

        val axisLeft = bar_chart_graphic.axisLeft
        axisLeft.isEnabled = false

        val axisRight = bar_chart_graphic.axisRight
        axisRight.isEnabled = false
    }
}