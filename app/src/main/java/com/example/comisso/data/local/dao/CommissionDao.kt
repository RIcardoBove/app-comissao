package com.example.comisso.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.comisso.data.local.entity.CommissionEntity
import java.time.LocalDate

@Dao
interface CommissionDao {

    @Insert
    suspend fun insert(commission: CommissionEntity)

    @Delete
    suspend fun delete(commission: CommissionEntity)

    @Query("SELECT *FROM commissions WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC")
    suspend fun getCommissionsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): List<CommissionEntity>

}
