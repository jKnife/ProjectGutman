package com.example.polydome.projectgutman.presentation.viewmodel

import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.usecase.CalculateGoalStateUseCase
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ActionViewModel
@Inject constructor(private val actionEntityDao: ActionEntityDao,
                    private val calculateGoalStateUseCase: CalculateGoalStateUseCase) {

    private val actionIds = BehaviorSubject.create<Int>().toSerialized()

    fun switchActionId(id: Int) {
        actionIds.onNext(id)
    }

    val name: Observable<String>
        get() = actionIds
            .switchMap { id -> actionEntityDao.getById(id) }
            .map { it.name }

    val goalState: Observable<String>
        get() = actionIds
            .switchMap { calculateGoalStateUseCase.calculateGoalState(it) }
            .map { it.toString() }

}