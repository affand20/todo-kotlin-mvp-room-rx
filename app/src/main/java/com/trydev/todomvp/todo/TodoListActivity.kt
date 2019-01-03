package com.trydev.todomvp.todo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.trydev.todomvp.R
import com.trydev.todomvp.model.Todo
import com.trydev.todomvp.model.TodoDatabase
import com.trydev.todomvp.todo.detail.TodoDetailActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_todo_list.*
import okhttp3.OkHttpClient

class TodoListActivity : AppCompatActivity(), TodoListContract.view {

    override lateinit var presenter: TodoListContract.presenter

    private val list:MutableList<Todo> = mutableListOf()

    private lateinit var adapter:TodoListAdapter
    private var db:TodoDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        Observable.fromCallable { initStetho() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        db = TodoDatabase.getInstance(this)

        presenter = TodoListPresenter(this, db?.todoDao())
        adapter = TodoListAdapter(list)

        rv_todo.layoutManager = LinearLayoutManager(this)
        rv_todo.adapter = adapter

        add_todo.setOnClickListener {
            presenter.createNewTodo()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun sendToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showListTodo(data: List<Todo>) {
        data.let {
            list.clear()
            list.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showDetailTodo(todo: Todo) { }

    override fun createNewTodo() {
        startActivity(Intent(this, TodoDetailActivity::class.java))
    }
}
