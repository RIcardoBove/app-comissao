package com.example.comisso.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.comisso.data.local.entity.CommissionServiceEntity

@Dao
interface CommissionServiceDao {

    @Insert
    suspend fun insert(commissionService: CommissionServiceEntity)

    @Delete
    suspend fun delete(commissionService: CommissionServiceEntity)

    @Query("""
        SELECT * FROM commission_services
        WHERE commissionId = :commissionId
    """)
    suspend fun getServicesByCommissionId(
        commissionId: Int
    ): List<CommissionServiceEntity>
}
