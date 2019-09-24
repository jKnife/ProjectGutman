package com.example.polydome.projectgutman.domain.model

sealed class GoalTrigger {
//    data class Duration(val duration: Long): GoalTrigger()
    data class Count(val count: Int): GoalTrigger()
}