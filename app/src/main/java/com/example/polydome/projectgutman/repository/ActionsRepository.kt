package com.example.polydome.projectgutman.repository

import com.example.polydome.projectgutman.domain.model.Action
import io.reactivex.Completable
import io.reactivex.Maybe

class ActionsRepository {

    fun findAction(actionId: Int): Maybe<Action> = Maybe.create {

    }

    fun updateAction(action: Action): Completable = Completable.create {

    }

}