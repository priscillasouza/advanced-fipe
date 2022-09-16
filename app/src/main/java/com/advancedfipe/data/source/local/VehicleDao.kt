package com.advancedfipe.data.source.local

import androidx.room.*
import com.advancedfipe.data.source.local.entity.VehicleEntity

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveVehicle(vehicle: VehicleEntity)

    @Update
    fun update(vehicle: VehicleEntity)

    @Query("SELECT * FROM vehicle_table WHERE favorite = 1")
    fun getFavorites(): List<VehicleEntity>
}