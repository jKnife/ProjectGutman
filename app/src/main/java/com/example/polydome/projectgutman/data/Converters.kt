package com.example.polydome.projectgutman.data

import androidx.room.TypeConverter
import com.example.polydome.projectgutman.domain.model.Difficulty

class Converters {

    @TypeConverter
    fun toTriggerType(value: String): TriggerType = TriggerType.valueOf(value)

    @TypeConverter
    fun fromTriggerType(triggerType: TriggerType): String = triggerType.name

    @TypeConverter
    fun toDifficulty(value: String): Difficulty = Difficulty.valueOf(value)

    @TypeConverter
    fun fromDifficulty(difficulty: Difficulty): String = difficulty.name

}