package com.example.polydome.projectgutman.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toTriggerType(value: String): TriggerType = TriggerType.valueOf(value)

    @TypeConverter
    fun fromTriggerType(triggerType: TriggerType): String = triggerType.name

}