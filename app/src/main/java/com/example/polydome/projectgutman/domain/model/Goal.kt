package com.example.polydome.projectgutman.domain.model

sealed class Goal {
    data class Duration(val current: Long, val target: Long): Goal()
    data class Count(val current: Int, val target: Int): Goal()
    data class Complete(val completed: Boolean): Goal()
}