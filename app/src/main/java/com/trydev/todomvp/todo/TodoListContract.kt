package com.trydev.todomvp.todo

import com.trydev.todomvp.BasePresenter
import com.trydev.todomvp.BaseView
import com.trydev.todomvp.model.Todo

interface TodoListContract {

    interface presenter:BasePresenter{
        fun getAllTodo()
        fun createNewTodo()
    }

    interface view:BaseView<TodoListContract.presenter>{
        fun showLoading()
        fun hideLoading()
        fun sendToast(message:String)
        fun showListTodo(data:List<Todo>)
        fun showDetailTodo(todo:Todo)
        fun createNewTodo()
    }
}