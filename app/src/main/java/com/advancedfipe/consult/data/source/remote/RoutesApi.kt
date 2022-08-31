package com.advancedfipe.consult.data.source.remote

import com.advancedfipe.consult.data.source.remote.entity.BrandResponse
import com.advancedfipe.consult.data.source.remote.entity.ModelsResponse
import com.advancedfipe.consult.data.source.remote.entity.ModelYearResponse
import com.advancedfipe.consult.data.source.remote.entity.VehicleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RoutesApi {
    @GET("{type}/marcas")
    suspend fun getBrands(@Path("type") type: String): Response<List<BrandResponse>>

    @GET("{type}/marcas/{brand}/modelos")
    suspend fun getModels(
        @Path("type") type: String,
        @Path("brand") brand: String,
    ): Response<ModelsResponse>

    @GET("{type}/marcas/{brand}/modelos/{model}/anos")
    suspend fun getModelYears(
        @Path("type") type: String,
        @Path("brand") brand: String,
        @Path("model") model: String
    ): Response<List<ModelYearResponse>>

    @GET("{type}/marcas/{brand}/modelos/{model}/anos/{year}")
    suspend fun getVehicle(
        @Path("type") type: String,
        @Path("brand") brand: String,
        @Path("model") model: String,
        @Path("year") year: String
    ): Response<VehicleResponse>
}