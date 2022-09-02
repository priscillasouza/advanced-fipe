package com.advancedfipe.consult.data.source.remote.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ModelsResponse (
    @SerializedName("modelos")
    val models: List<ModelResponse>
)

@Keep
data class ModelResponse(
    @SerializedName("nome")
    val name: String,
    @SerializedName("codigo")
    val code: String
)