package com.advancedfipe.consult.domain.mappers

import com.advancedfipe.consult.domain.model.Vehicle
import com.advancedfipe.data.source.local.entity.VehicleEntity

class VehicleEntityToModelMapper : IMapper<VehicleEntity, Vehicle> {
    override fun transform(entity: VehicleEntity): Vehicle {
        return Vehicle(
            fipeCode = entity.fipeCode,
            price = entity.price,
            brand = entity.brand,
            model = entity.model,
            modelYear = entity.modelYear,
            fuel = entity.fuel,
            referenceMonth = entity.referenceMonth,
            vehicleType = entity.vehicleType,
            acronymFuel = entity.acronymFuel,
            favorite = entity.favorite
        )
    }
}