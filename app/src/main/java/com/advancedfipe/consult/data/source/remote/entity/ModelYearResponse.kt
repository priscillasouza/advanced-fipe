package com.advancedfipe.consult.data.source.remote.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ModelYearResponse(
    @SerializedName("nome")
    val name: String,
    @SerializedName("codigo")
    val code: String
)