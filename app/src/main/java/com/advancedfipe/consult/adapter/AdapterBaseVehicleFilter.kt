package com.advancedfipe.consult.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.ThemedSpinnerAdapter
import com.advancedfipe.consult.domain.model.BaseVehicleFilter

@SuppressLint("NewApi")
class AdapterBaseVehicleFilter(
    context: Context,
    private val listFields: List<BaseVehicleFilter>,
) : ArrayAdapter<BaseVehicleFilter>(
    context,
    android.R.layout.simple_spinner_dropdown_item,
    listFields
), ThemedSpinnerAdapter {
    override fun getItem(position: Int): BaseVehicleFilter? {
        return listFields[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listFields.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        super.getView(position, convertView, parent)
        val view = super.getView(position, convertView, parent)
        val nameFields = view.findViewById<TextView>(android.R.id.text1)
        nameFields.setText(listFields[position].name)
        return view
    }
}