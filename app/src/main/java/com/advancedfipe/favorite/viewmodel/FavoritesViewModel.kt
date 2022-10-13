package com.advancedfipe.favorite.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advancedfipe.consult.data.repository.VehicleRepositoryImpl
import com.advancedfipe.consult.domain.model.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoritesViewModel(
    context: Context
) : ViewModel() {

    private var vehicleRepository = VehicleRepositoryImpl(context)

    private var _vehicleFavorites: MutableLiveData<List<Vehicle>> = MutableLiveData()
    val vehicleFavorites: LiveData<List<Vehicle>> = _vehicleFavorites

    private var _updateFavorite: MutableLiveData<Vehicle> = MutableLiveData()
    val updateFavorite: LiveData<Vehicle> = _updateFavorite

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            vehicleRepository.getFavorites().catch { exception ->
                _error.postValue(exception.message)
            }.collect{
                _vehicleFavorites.postValue(it)
            }
        }
    }

    fun updateFavorite(vehicle: Vehicle) {
        viewModelScope.launch(Dispatchers.IO) {
            if(!vehicle.favorite) {
                vehicleRepository.update(vehicle).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _updateFavorite.postValue(it)
                }
            } else {
                vehicleRepository.update(vehicle).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _updateFavorite.postValue(it)
                }
            }
        }
    }
}