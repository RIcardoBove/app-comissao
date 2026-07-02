package com.example.comisso.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.comisso.data.repository.CommissionRepository
import com.example.comisso.ui.viewmodel.CommissionListViewModel

class CommissionListViewFactory(private val repository: CommissionRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T{
        if (modelClass.isAssignableFrom(CommissionListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommissionListViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}