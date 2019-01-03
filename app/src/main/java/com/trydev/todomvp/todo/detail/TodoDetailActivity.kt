package com.trydev.todomvp.todo.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.trydev.todomvp.R
import com.trydev.todomvp.model.Todo
import com.trydev.todomvp.model.TodoDao
import com.trydev.todomvp.model.TodoDatabase
import kotlinx.android.synthetic.main.activity_detail.*

class TodoDetailActivity : AppCompatActivity(), TodoDetailContract.view {

    override lateinit var presenter: TodoDetailContract.presenter

    private var todo = Todo()
    private var db:TodoDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db = TodoDatabase.getInstance(this)

        presenter = TodoDetailPresenter(this, db?.todoDao())

        btn_save.setOnClickListener {
            todo.isDone = false
            todo.judul = judul_todo.text.toString()
            presenter.insertToDb(todo)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun sendToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onComplete() {
        onBackPressed()
    }
}
