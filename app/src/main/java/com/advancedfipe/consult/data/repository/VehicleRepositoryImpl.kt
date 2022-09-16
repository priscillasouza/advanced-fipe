package com.advancedfipe.consult.data.repository

import android.content.Context
import com.advancedfipe.BuildConfig
import com.advancedfipe.consult.data.source.remote.api.Api
import com.advancedfipe.consult.domain.mappers.*
import com.advancedfipe.consult.domain.model.Brand
import com.advancedfipe.consult.domain.model.Model
import com.advancedfipe.consult.domain.model.ModelYear
import com.advancedfipe.consult.domain.model.Vehicle
import com.advancedfipe.consult.domain.repository.IVehicleRepository
import com.advancedfipe.data.source.local.VehicleDao
import com.advancedfipe.data.source.local.VehicleDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class VehicleRepositoryImpl(
    private val context: Context? = null,
) : IVehicleRepository {

    private var vehicleRoute = Api(BuildConfig.API_URL).create()

    private var vehicleDao: VehicleDao? = context?.run {
        VehicleDatabase.getDatabase(this.applicationContext).vehicleDao()
    }

    private var vehicleEntityToModelMapper = VehicleEntityToModelMapper()
    private var vehicleToEntityMapper = VehicleToEntityMapper()

    override suspend fun getBrands(type: String): Flow<List<Brand>> {
        return flow {
            vehicleRoute.getBrands(type).let { brandResponse ->
                if (brandResponse.isSuccessful && brandResponse.body() != null) {
                    BrandToModelMapper().transform(brandResponse.body()!!)
                } else {
                    throw HttpException(brandResponse)
                }
            }.let {
                emit(it)
            }
        }
    }

    override suspend fun getModels(type: String, brand: String): Flow<List<Model>> {
        return flow {
            vehicleRoute.getModels(type, brand).let { modelResponse ->
                if (modelResponse.isSuccessful && modelResponse.body() != null) {
                    ModelToModelMapper().transform(modelResponse.body()?.models.orEmpty())
                } else {
                    throw HttpException(modelResponse)
                }
            }.let {
                emit(it)
            }
        }
    }

    override suspend fun getModelYears(
        type: String,
        brand: String,
        model: String,
    ): Flow<List<ModelYear>> {
        return flow {
            vehicleRoute.getModelYears(type, brand, model).let { modelYearResponse ->
                if (modelYearResponse.isSuccessful && modelYearResponse.body() != null) {
                    ModelYearToModelMapper().transform(modelYearResponse.body()!!)
                } else {
                    throw HttpException(modelYearResponse)
                }
            }.let {
                emit(it)
            }
        }
    }

    override suspend fun getVehicle(
        type: String,
        brand: String,
        model: String,
        modelYear: String,
    ): Flow<Vehicle> {
        return flow {
            vehicleRoute.getVehicle(type, brand, model, modelYear).let { vehicleResponse ->
                if (vehicleResponse.isSuccessful && vehicleResponse.body() != null) {
                    VehicleToModelMapper().transform(vehicleResponse.body()!!)
                } else {
                    throw HttpException(vehicleResponse)
                }
            }.let {
                emit(it)
            }
        }
    }

    override suspend fun saveVehicle(vehicle: Vehicle): Flow<Vehicle> {
        return flow {
            vehicleToEntityMapper.transform(vehicle)
                .also {
                    vehicleDao?.saveVehicle(it) ?: throw Exception("VehicleDAO is null")
                }
        }
    }

    override suspend fun update(vehicle: Vehicle): Flow<Vehicle> {
        return flow {
            vehicleToEntityMapper.transform(vehicle)
                .also {
                    vehicleDao?.update(it) ?: throw Exception("VehicleDAO is null")
                }
        }
    }
}