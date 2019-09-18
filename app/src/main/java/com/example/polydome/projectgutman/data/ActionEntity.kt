package com.example.polydome.projectgutman.data

import com.example.polydome.projectgutman.domain.model.Difficulty

data class ActionEntity(
    val id: Int,
    val name: String,
    val difficulty: Difficulty,
    val triggerType: TriggerType,
    val currentValue: Long,
    val targetValue: Long
)