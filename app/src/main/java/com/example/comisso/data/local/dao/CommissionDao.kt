package com.example.comisso.data.local.dao

import androidx.room.Insert
import com.example.comisso.data.local.entity.CommissionEntity

interface CommissionDao {

    @Insert
    suspend fun insert(comission: CommissionEntity)
}