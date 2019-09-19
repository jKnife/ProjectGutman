package com.example.polydome.projectgutman.presentation.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.polydome.projectgutman.R
import com.example.polydome.projectgutman.presentation.viewmodel.ActionViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ActionViewHolder(itemView: View, val viewModel: ActionViewModel) : RecyclerView.ViewHolder(itemView) {

    private val compositeDisposable = CompositeDisposable()

    fun onAttach() {
        compositeDisposable.add(viewModel.name
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                itemView.findViewById<TextView>(R.id.action_entity_name).text = it
            })
    }

    fun onDetach() {
        compositeDisposable.clear()
    }

}