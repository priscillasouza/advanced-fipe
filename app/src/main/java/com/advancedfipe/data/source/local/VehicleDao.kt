package com.advancedfipe.data.source.local

import androidx.room.*
import com.advancedfipe.data.source.local.entity.VehicleEntity

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVehicle(vehicle: VehicleEntity)

    @Update
    fun update(vehicle: VehicleEntity)

    @Query("SELECT * FROM vehicle_table WHERE favorite = 1")
    fun getFavorites(): List<VehicleEntity>

    @Query("SELECT * FROM vehicle_table")
    fun getAllVehicle(): List<VehicleEntity>

    @Query("SELECT * FROM vehicle_table WHERE fipeCode = :fipeCode")
    fun getVehicleById(fipeCode: String): List<VehicleEntity>
}