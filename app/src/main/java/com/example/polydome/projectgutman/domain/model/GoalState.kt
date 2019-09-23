package com.example.polydome.projectgutman.domain.model

sealed class GoalState {
    data class Count(val currentCount: Int): GoalState()
//    data class Duration(val currentDuration: Long): GoalState()
//    data class Complete(val completed: Boolean): GoalState()
}