package com.advancedfipe.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_table")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = false)
    val fipeCode: String,
    val price: String,
    val brand: String,
    val model: String,
    val modelYear: Int,
    val fuel: String,
    val referenceMonth: String,
    val vehicleType: Int,
    val acronymFuel: String,
    val favorite: Boolean = false
)