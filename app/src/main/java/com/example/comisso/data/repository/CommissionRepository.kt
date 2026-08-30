package com.example.comisso.data.repository

import com.example.comisso.data.local.dao.CommissionDao
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.data.local.relation.CommissionWithServices
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class CommissionRepository(private val commissionDao: CommissionDao) {

    suspend fun delete(commission: CommissionEntity) = commissionDao.delete(commission)

    fun getCommissionsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<CommissionWithServices>> = commissionDao.getCommissionsByDate(startDate, endDate)

}
