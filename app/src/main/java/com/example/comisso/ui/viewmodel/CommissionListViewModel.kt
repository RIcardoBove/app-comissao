package com.example.comisso.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.data.repository.CommissionRepository
import java.time.LocalDate

class CommissionListViewModel(private val repository: CommissionRepository): ViewModel() {

    suspend fun getCommissionsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): List<CommissionEntity> {
        return repository.getCommissionsByDate(startDate, endDate)
    }

    fun calculateTotal(
        commissions: List<CommissionEntity>
    ): Double {
        return commissions.sumOf { it.value }
    }

}