package com.example.polydome.projectgutman.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polydome.projectgutman.R
import com.example.polydome.projectgutman.data.ActionEntityDao
import com.example.polydome.projectgutman.presentation.viewmodel.ActionViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

class ActionsListAdapter
@Inject constructor(
    @Named("ActivityContext") private val activityContext: Context,
    private val actionViewModelProvider: Provider<ActionViewModel>,
    private val actionEntityDao: ActionEntityDao
) : RecyclerView.Adapter<ActionViewHolder>() {

    private var idsList: List<Int> = listOf()
    private val compositeDisposable = CompositeDisposable()

    private fun updateIdsList(newList: List<Int>) {
        idsList = newList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        val inflater = LayoutInflater.from(activityContext)
        val view = inflater.inflate(R.layout.action_entry, parent)
        return ActionViewHolder(view, actionViewModelProvider.get())
    }

    override fun getItemCount(): Int = idsList.size

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
        holder.viewModel.switchActionId(idsList[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        compositeDisposable.add(
            actionEntityDao.getIds().subscribe(this::updateIdsList)
        )
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        compositeDisposable.clear()
    }

    override fun onViewDetachedFromWindow(holder: ActionViewHolder) {
        super.onViewDetachedFromWindow(holder)

        holder.onAttach()
    }

    override fun onViewAttachedToWindow(holder: ActionViewHolder) {
        super.onViewAttachedToWindow(holder)

        holder.onDetach()
    }

}

