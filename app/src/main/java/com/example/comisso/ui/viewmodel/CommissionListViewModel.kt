package com.example.comisso.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.data.repository.CommissionRepository
import com.example.comisso.data.local.relation.CommissionWithServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate

class CommissionListViewModel(private val repository: CommissionRepository) : ViewModel() {

     fun getCommissionsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<CommissionWithServices>> {
        return repository.getCommissionsByDate(startDate, endDate)
    }

    fun calculateTotal(
        commissions: List<CommissionWithServices>
    ): Double {
        return commissions.sumOf { commission ->
            commission.services.sumOf { service ->
                service.value
            }
        }

    }

    fun deleteCommission(commission: CommissionEntity) {
        viewModelScope.launch {
            repository.delete(commission)

        }
    }


}