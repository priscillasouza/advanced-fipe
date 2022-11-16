package com.advancedfipe.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "vehicle_table", primaryKeys = ["fipeCode", "referenceMonth"])
data class VehicleEntity(
    val fipeCode: String,
    val price: String,
    val brand: String,
    val model: String,
    val modelYear: Int,
    val fuel: String,
    val referenceMonth: String,
    val vehicleType: Int,
    val acronymFuel: String,
    var favorite: Boolean = false
)