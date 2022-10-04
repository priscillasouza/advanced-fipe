package com.advancedfipe.result.viewmodel

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

class ResultViewModel(
    context: Context
) : ViewModel() {

    private var vehicleRepository = VehicleRepositoryImpl(context)

    private var _updateVehicle = MutableLiveData<Vehicle>()
    val updateVehicle: LiveData<Vehicle> = _updateVehicle

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun updateFavoriteVehicle(vehicle: Vehicle) {
        viewModelScope.launch(Dispatchers.IO) {
                vehicleRepository.update(vehicle).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _updateVehicle.postValue(it)
                }
        }
    }
}