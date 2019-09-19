package com.example.polydome.projectgutman.presentation.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.polydome.projectgutman.presentation.ui.ActionsListAdapter
import com.example.polydome.projectgutman.presentation.ui.common.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var actionsListAdapter: ActionsListAdapter

    lateinit var recyclerView: RecyclerView

    val layoutResId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presentationComponent.inject(this)

        setContentView(layoutResId)

        recyclerView.adapter = actionsListAdapter
    }

}