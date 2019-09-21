package com.example.polydome.projectgutman.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "goalTrigger")
class GoalTriggerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ForeignKey(childColumns = ["actionId"], parentColumns = ["id"], entity = ActionEntity::class)
    @ColumnInfo(name = "actionId")
    val actionId: Int?,

    @ColumnInfo(name = "triggerType")
    val triggerType: TriggerType,

    @ColumnInfo(name = "value")
    val value: Long
)

