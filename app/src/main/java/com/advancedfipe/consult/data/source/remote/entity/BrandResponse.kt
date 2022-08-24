package com.advancedfipe.consult.data.source.remote.entity

import com.google.gson.annotations.SerializedName

class BrandResponse(
    @SerializedName("nome")
    val name: String,
    @SerializedName("codigo")
    val code: String
)