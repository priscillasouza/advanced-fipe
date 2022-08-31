package com.advancedfipe.consult.data.source.remote.entity

import com.google.gson.annotations.SerializedName

class ModelsResponse (
    @SerializedName("modelos")
    val models: List<ModelResponse>
)

class ModelResponse(
    @SerializedName("nome")
    val name: String,
    @SerializedName("codigo")
    val code: String
)