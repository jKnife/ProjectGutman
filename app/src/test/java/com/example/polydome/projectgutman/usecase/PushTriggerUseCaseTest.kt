package com.example.polydome.projectgutman.usecase

import com.example.polydome.projectgutman.domain.model.Goal
import com.example.polydome.projectgutman.domain.model.GoalTrigger
import com.example.polydome.projectgutman.repository.GoalRepository
import com.example.polydome.projectgutman.repository.GoalTriggerRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.BehaviorSubject
import org.junit.Before
import org.junit.Test

class PushTriggerUseCaseTest {
    val ACTION_ID = 3
    val TRIGGER_VALUE: Long = 42
    val GOAL = Goal.Count(40)
    val GOAL_TRIGGER = GoalTrigger.Count(TRIGGER_VALUE.toInt())

    private lateinit var SUT: PushTriggerUseCase
    private lateinit var goalTriggerRepository: GoalTriggerRepository
    private lateinit var goalRepository: GoalRepository

    private lateinit var testObserver: TestObserver<Void>

    @Before
    fun setup() {
        goalTriggerRepository = mockk()
        goalRepository = mockk()
        SUT = PushTriggerUseCase(goalTriggerRepository, goalRepository)

        testObserver = TestObserver()
    }

    @Test
    fun pushTrigger_noGoalEmitted_emitsError() {
        val goalSubject = BehaviorSubject.create<Goal>()

        every { goalRepository.getByActionId(ACTION_ID) } returns goalSubject

        SUT.pushTrigger(ACTION_ID, TRIGGER_VALUE)
            .subscribe(testObserver)

        testObserver.assertError(NoSuchActionException::class.java)
    }

    @Test
    fun pushTrigger_actionEmitted_insertsTriggerAndCompletes() {
        val goalSubject = BehaviorSubject.create<Goal>()

        every { goalRepository.getByActionId(ACTION_ID) } returns goalSubject
        every { goalTriggerRepository.insert(ACTION_ID,GOAL_TRIGGER) } returns Completable.complete()

        goalSubject.onNext(GOAL)

        SUT.pushTrigger(ACTION_ID, TRIGGER_VALUE)
            .subscribe(testObserver)

        val expectedGoalTrigger = GoalTrigger.Count(TRIGGER_VALUE.toInt())

        verify { goalTriggerRepository.insert(ACTION_ID, expectedGoalTrigger) }
        testObserver.assertComplete()
    }

}