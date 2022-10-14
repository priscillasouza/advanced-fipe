package com.advancedfipe.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.advancedfipe.consult.domain.model.Vehicle
import com.advancedfipe.databinding.FavoriteItemBinding
import com.advancedfipe.favorite.presentation.FavoritesFragmentDirections

class ListFavoritesVehicleAdapter :
    RecyclerView.Adapter<ListFavoritesVehicleAdapter.VehicleViewHolder>() {

    private var vehicleList = arrayListOf<Vehicle>()
    private var onClickFavorite: ((Vehicle) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding =
            FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding, onClickFavorite)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.onBind(vehicleList[position])
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

    fun setList(list: List<Vehicle>) {
        if (list.isNotEmpty()) {
            vehicleList.clear()
            vehicleList.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun setOnClickFavorite(run: (Vehicle) -> Unit) {
        onClickFavorite = run
    }

    class VehicleViewHolder(
        private val layout: FavoriteItemBinding,
        private val onClickFavorite: ((Vehicle) -> Unit?)?,
    ) :
        RecyclerView.ViewHolder(layout.root) {
        fun onBind(vehicle: Vehicle) {
            layout.apply {
                textViewModelFavorite.text = vehicle.model
                textViewBrandFavorite.text = vehicle.brand
                textViewPriceFavorite.text = vehicle.price
                textViewReferencesPeriodFavorite.text = vehicle.referenceMonth

                checkBoxItemFavorite.isChecked = vehicle.favorite
                checkBoxItemFavorite.setOnClickListener {
                    onClickFavorite?.invoke(vehicle.copy(favorite = vehicle.favorite.not()))
                }

                cardViewFavorites.setOnClickListener {
                    val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(vehicle)
                    cardViewFavorites.findNavController().navigate(action)
                }
            }
        }
    }
}