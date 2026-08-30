package com.example.comisso.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "commission_services",
    foreignKeys = [
        ForeignKey(
        entity = CommissionEntity::class,
        parentColumns = ["id"],
        childColumns = ["commissionId"],
        onDelete = ForeignKey.CASCADE
        )
    ])
data class CommissionServiceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val commissionId: Int,

    val service: String,

    val value: Double,

    val isCustomService: Boolean
)