package com.trydev.todomvp.todo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trydev.todomvp.R
import com.trydev.todomvp.model.Todo

class TodoListAdapter(val list:List<Todo>):RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_items, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {

        private val title = view.findViewById<TextView>(R.id.title_todo)

        fun bindView(todo:Todo){
            title.text = todo.judul
        }
    }
}