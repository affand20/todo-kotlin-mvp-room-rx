package com.trydev.todomvp.todo.detail

import android.util.Log
import com.trydev.todomvp.model.Todo
import com.trydev.todomvp.model.TodoDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

class TodoDetailPresenter(val view:TodoDetailContract.view, val todoDao: TodoDao?):TodoDetailContract.presenter {
    override fun validate(todo:Todo):Boolean {
        if (todo.judul.isBlank()) return false
        return true
    }

    override fun insertToDb(todo: Todo) {
        if (validate(todo)){
            Observable.fromCallable { todoDao?.insert(todo) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    Log.i("COMPLETE", "On Completed")
                    view.onComplete()
                }
                .doOnError {e ->
                    Log.e("ERROR", "On Error ${e.printStackTrace()}")
                    view.sendToast("On Error ${e.printStackTrace()}")
                }
                .subscribe()
        } else{
            view.sendToast("Judul tidak boleh kosong")
        }
    }

    override fun start() {

    }
}