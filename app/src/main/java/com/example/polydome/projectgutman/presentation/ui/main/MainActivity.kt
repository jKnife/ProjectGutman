package com.example.polydome.projectgutman.presentation.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.polydome.projectgutman.R
import com.example.polydome.projectgutman.presentation.ui.ActionsListAdapter
import com.example.polydome.projectgutman.presentation.ui.common.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var actionsListAdapter: ActionsListAdapter

    private lateinit var recyclerView: RecyclerView

    private val layoutResId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presentationComponent.inject(this)

        setContentView(layoutResId)

        recyclerView = findViewById(R.id.actions_rv)

        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        recyclerView.adapter = actionsListAdapter
    }

}