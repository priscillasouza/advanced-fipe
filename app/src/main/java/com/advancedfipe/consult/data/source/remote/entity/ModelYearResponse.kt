package com.advancedfipe.consult.data.source.remote.entity

import com.google.gson.annotations.SerializedName

class ModelYearResponse(
    @SerializedName("nome")
    val name: String,
    @SerializedName("codigo")
    val code: String
)