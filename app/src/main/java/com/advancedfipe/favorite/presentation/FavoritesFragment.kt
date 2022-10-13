package com.advancedfipe.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.advancedfipe.R
import com.advancedfipe.databinding.FragmentFavoritesBinding
import com.advancedfipe.favorite.adapter.ListFavoritesVehicleAdapter
import com.advancedfipe.favorite.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.favorite_item.*

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var adapterRecyclerViewListFavorites: ListFavoritesVehicleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        favoritesViewModel = FavoritesViewModel(requireContext())
        favoritesViewModel.getFavorites()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserver()
        setAdpaterListFavorites()
    }

    private fun onObserver() {
        favoritesViewModel.apply {
            vehicleFavorites.observe(viewLifecycleOwner) { vehicle ->
                adapterRecyclerViewListFavorites.setList(vehicle)
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), getString(R.string.text_fail_in_favorites_list), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        favoritesViewModel.apply {
            updateFavorite.observe(viewLifecycleOwner) { vehicle ->
                adapterRecyclerViewListFavorites.setList(arrayListOf())
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), getString(R.string.text_failure_unfavorite_item), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setAdpaterListFavorites() {
        binding.recyclerViewListFavorites.apply {
            adapterRecyclerViewListFavorites = ListFavoritesVehicleAdapter()
            adapterRecyclerViewListFavorites.setOnClickFavorite {
                    if (!check_box_item_favorite.isChecked) {
                        favoritesViewModel.updateFavorite(vehicle = it)
                        Toast.makeText(context,
                            context.getString(R.string.text_unfavored_successfully), Toast.LENGTH_SHORT)
                            .show()
                }
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterRecyclerViewListFavorites
        }
    }
}