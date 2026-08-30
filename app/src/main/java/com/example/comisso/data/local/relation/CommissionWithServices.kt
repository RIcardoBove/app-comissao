package com.example.comisso.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.data.local.entity.CommissionServiceEntity

data class CommissionWithServices(
    @Embedded
    val commission: CommissionEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "commissionId"
    )
    val services: List<CommissionServiceEntity>
)