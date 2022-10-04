package com.advancedfipe.consult.domain.repository

import com.advancedfipe.consult.domain.model.Brand
import com.advancedfipe.consult.domain.model.Model
import com.advancedfipe.consult.domain.model.ModelYear
import com.advancedfipe.consult.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface IVehicleRepository {

    suspend fun getBrands(type: String): Flow<List<Brand>>

    suspend fun getModels(type: String, brand: String): Flow<List<Model>>

    suspend fun getModelYears(type: String, brand: String, model: String): Flow<List<ModelYear>>

    suspend fun getVehicle(
        type: String,
        brand: String,
        model: String,
        modelYear: String,
    ): Flow<Vehicle>

    suspend fun saveVehicle(vehicle: Vehicle): Flow<Vehicle>

    suspend fun update(vehicle: Vehicle): Flow<Vehicle>
}