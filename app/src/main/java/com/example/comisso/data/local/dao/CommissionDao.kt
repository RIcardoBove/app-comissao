package com.example.comisso.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.comisso.data.local.relation.CommissionWithServices
import com.example.comisso.data.local.entity.CommissionEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface CommissionDao {

    @Insert
    suspend fun insert(commission: CommissionEntity)

    @Delete
    suspend fun delete(commission: CommissionEntity)

    @Transaction
    @Query("""
    SELECT * FROM commissions
    WHERE date BETWEEN :startDate AND :endDate
    ORDER BY date ASC
""")
    fun getCommissionsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<CommissionWithServices>>

}
