package com.advancedfipe.consult.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advancedfipe.consult.data.repository.VehicleRepositoryImpl
import com.advancedfipe.consult.domain.model.Brand
import com.advancedfipe.consult.domain.model.Model
import com.advancedfipe.consult.domain.model.ModelYear
import com.advancedfipe.consult.domain.model.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ConsultViewModel : ViewModel() {

    private var vehicleRepository: VehicleRepositoryImpl = VehicleRepositoryImpl()

    private var _brands = MutableLiveData<List<Brand>>()
    val brands: LiveData<List<Brand>> = _brands

    private var _models = MutableLiveData<List<Model>>()
    val models: LiveData<List<Model>> = _models

    private var _modelYears = MutableLiveData<List<ModelYear>>()
    val modelYears: LiveData<List<ModelYear>> = _modelYears

    private var _resultVehicle = MutableLiveData<Vehicle>()
    val resultVehicle: LiveData<Vehicle> = _resultVehicle

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getBrands(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (type.isNotBlank()) {
                vehicleRepository.getBrands(type).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _brands.postValue(it)
                }
            }
        }
    }

    fun getModels(type: String?, brand: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (type != null) {
                vehicleRepository.getModels(type, brand)
                    .catch { exception ->
                        _error.postValue(exception.message)
                    }.collect {
                        _models.postValue(it)
                    }
            }
        }
    }

    fun getModelYears(type: String?, brand: String, model: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (type != null)
                vehicleRepository.getModelYears(type,
                    brand,
                    model).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _modelYears.postValue(it)
                }
        }
    }

    fun getVehicle(type: String?, brand: String, model: String, year: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (type != null)
                vehicleRepository.getVehicle(type,
                    brand,
                    model,
                    year).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _resultVehicle.postValue(it)
                }
        }
    }
}