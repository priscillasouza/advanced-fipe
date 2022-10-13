package com.advancedfipe.consult.domain.mappers

import com.advancedfipe.consult.data.source.remote.entity.VehicleResponse
import com.advancedfipe.consult.domain.model.Vehicle

class VehicleToModelMapper: IMapper<VehicleResponse, Vehicle> {
    override fun transform(entity: VehicleResponse): Vehicle {
        return Vehicle(
            fipeCode = entity.fipeCode,
            modelYear = entity.modelYear,
            model = entity.model,
            brand = entity.brand,
            price = entity.price,
            acronymFuel = entity.acronymFuel,
            fuel = entity.acronymFuel,
            referenceMonth = entity.referenceMonth,
            vehicleType = entity.vehicleType,
            favorite = false
        )
    }
}