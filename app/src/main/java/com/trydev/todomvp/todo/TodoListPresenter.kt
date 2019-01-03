package com.trydev.todomvp.todo

import android.util.Log
import io.reactivex.Observable
import com.trydev.todomvp.model.TodoDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TodoListPresenter(val view:TodoListContract.view, val todoDao: TodoDao?) :TodoListContract.presenter {

    override fun getAllTodo() {
        view.showLoading()
        Observable.fromCallable { todoDao?.getAll() }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (it.size>0){
                    view.hideLoading()
                    view.showListTodo(it)
                } else{
                    view.hideLoading()
                    view.sendToast("Data kosong")
                }
            }
            .doOnError { e ->
                view.sendToast("Error ${e.printStackTrace()}")
            }
            .subscribe()
    }

    override fun start() {
        getAllTodo()
    }

    override fun createNewTodo() {
        view.createNewTodo()
    }

}