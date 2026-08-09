package com.example.comisso.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.data.repository.CommissionRepository
import kotlinx.coroutines.launch
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

    fun deleteCommission(commission: CommissionEntity) {
        viewModelScope.launch {
            repository.delete(commission)
        }
    }


}