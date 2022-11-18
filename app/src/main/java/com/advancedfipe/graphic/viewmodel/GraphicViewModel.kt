package com.advancedfipe.graphic.viewmodel

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

class GraphicViewModel(
    context: Context
): ViewModel() {

    private var vehicleRepository = VehicleRepositoryImpl(context)

    private var _vehicle = MutableLiveData<List<Vehicle>>()
    val vehicle: LiveData<List<Vehicle>> = _vehicle

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getVehicleById(fipeCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (fipeCode.isNotBlank()) {
                vehicleRepository.getVehicleById(fipeCode).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _vehicle.postValue(it)
                }
            }
        }
    }
}