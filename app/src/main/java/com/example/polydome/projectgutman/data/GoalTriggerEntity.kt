package com.example.polydome.projectgutman.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.polydome.projectgutman.domain.model.GoalTrigger

@Entity(tableName = "goalTrigger")
class GoalTriggerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "goalTriggerType") val goalTriggerType: GoalTrigger,
    @ColumnInfo(name = "value") val value: Long
)

