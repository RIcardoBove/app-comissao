package com.example.comisso.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "commissions")

data class CommissionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val car: String,
    val service: String,
    val value: Double,
    val isCustomService: Boolean
)
