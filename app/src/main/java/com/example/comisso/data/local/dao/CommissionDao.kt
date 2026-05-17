package com.example.comisso.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.comisso.data.local.entity.CommissionEntity

@Dao
interface CommissionDao {

    @Insert
    suspend fun insert(commission: CommissionEntity)

    @Query("SELECT * FROM commissions")
    suspend fun getAll(): List<CommissionEntity>
}
