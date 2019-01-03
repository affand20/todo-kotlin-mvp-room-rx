package com.trydev.todomvp.todo.detail

import com.trydev.todomvp.BasePresenter
import com.trydev.todomvp.BaseView
import com.trydev.todomvp.model.Todo

interface TodoDetailContract {

    interface presenter:BasePresenter{
        fun validate(todo:Todo):Boolean
        fun insertToDb(todo: Todo)
    }

    interface view:BaseView<TodoDetailContract.presenter>{
        fun sendToast(message:String)
        fun onComplete()
    }
}