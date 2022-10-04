package com.advancedfipe.consult.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
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
):Parcelable