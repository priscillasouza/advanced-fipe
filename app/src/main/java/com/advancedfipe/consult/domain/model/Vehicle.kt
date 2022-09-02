package com.advancedfipe.consult.domain.model

import java.io.Serializable

open class Vehicle(
    val price: String,
    val brand: String,
    val model: String,
    val modelYear: Int,
    val fuel: String,
    val fipeCode: String,
    val referenceMonth: String,
    val vehicleType: Int,
    val acronymFuel: String
):Serializable