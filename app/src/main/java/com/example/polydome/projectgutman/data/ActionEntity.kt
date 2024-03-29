package com.example.polydome.projectgutman.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actionEntity")
data class ActionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "triggerType") val triggerType: TriggerType,
    @ColumnInfo(name = "currentValue") val currentValue: Long,
    @ColumnInfo(name = "targetValue") val targetValue: Long
)