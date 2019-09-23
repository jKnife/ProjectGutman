package com.example.polydome.projectgutman.domain.model

sealed class Goal {
    data class Count(val targetCount: Int): Goal()
//    data class Duration(val targetMilliseconds: Long): Goal()
//    object Complete: Goal()
}