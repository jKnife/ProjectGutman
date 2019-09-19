package com.example.polydome.projectgutman.presentation.viewmodel

import com.example.polydome.projectgutman.data.ActionEntityDao
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class ActionViewModel(private val actionEntityDao: ActionEntityDao) {
    private val actionIds = BehaviorSubject.create<Int>().toSerialized()

    fun switchActionId(id: Int) {
        actionIds.onNext(id)
    }

    val name: Observable<String>
        get() = actionIds
            .switchMap { id -> actionEntityDao.getById(id) }
            .map { it.name }
}