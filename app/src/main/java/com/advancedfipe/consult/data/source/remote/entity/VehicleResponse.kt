package com.advancedfipe.consult.data.source.remote.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VehicleResponse(
    @SerializedName("Valor")
    val price: String,
    @SerializedName("Marca")
    val brand: String,
    @SerializedName("Modelo")
    val model: String,
    @SerializedName("AnoModelo")
    val modelYear: Int,
    @SerializedName("Combustivel")
    val fuel: String,
    @SerializedName("CodigoFipe")
    val fipeCode: String,
    @SerializedName("MesReferencia")
    val referenceMonth: String,
    @SerializedName("TipoVeiculo")
    val vehicleType: Int,
    @SerializedName("SiglaCombustivel")
    val acronymFuel: String
)