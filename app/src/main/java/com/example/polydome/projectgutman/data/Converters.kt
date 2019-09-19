package com.example.polydome.projectgutman.data

import androidx.room.TypeConverter
import com.example.polydome.projectgutman.domain.model.Difficulty
import java.lang.Exception

class UnimplementedTriggerTypeValueException(value: Byte): Exception(
    "TriggerType of value [$value] unknown"
)

class UnimplementedTriggerTypeException(triggerType: TriggerType): Exception(
    "TriggerType [$triggerType] unknown"
)

class UnimplementedDifficultyValueException(value: Byte): Exception(
    "Difficulty of value [$value] unknown"
)

class Converters {

    @TypeConverter
    fun toTriggerType(triggerTypeValue: Byte): TriggerType = when (triggerTypeValue) {
        0.toByte() -> TriggerType.COUNT
        else -> throw UnimplementedTriggerTypeValueException(triggerTypeValue)
    }

    @TypeConverter
    fun fromTriggerType(triggerType: TriggerType): Byte = when (triggerType) {
        TriggerType.COUNT -> 0
    }

    @TypeConverter
    fun toDifficulty(difficultyValue: Byte): Difficulty = when (difficultyValue) {
        0.toByte() -> Difficulty.EASY
        1.toByte() -> Difficulty.MEDIUM
        2.toByte() -> Difficulty.HARD
        else -> throw UnimplementedDifficultyValueException(difficultyValue)
    }

    @TypeConverter
    fun fromDifficulty(difficulty: Difficulty): Byte = when (difficulty) {
        Difficulty.EASY -> 0
        Difficulty.MEDIUM -> 1
        Difficulty.HARD -> 2
    }

}