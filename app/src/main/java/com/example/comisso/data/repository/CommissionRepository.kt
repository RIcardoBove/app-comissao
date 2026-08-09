package com.example.comisso.data.repository

import com.example.comisso.data.local.dao.CommissionDao
import com.example.comisso.data.local.entity.CommissionEntity
import java.time.LocalDate

class CommissionRepository(private val commissionDao: CommissionDao) {

    suspend fun delete(commission: CommissionEntity) = commissionDao.delete(commission)

    suspend fun getCommissionsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): List<CommissionEntity> = commissionDao.getCommissionsByDate(startDate, endDate)

}
