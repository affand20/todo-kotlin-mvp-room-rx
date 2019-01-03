package com.trydev.todomvp.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll():List<Todo>

    @Insert
    fun insert(todo:Todo)

    @Delete
    fun delete(todo:Todo)

    @Query("UPDATE todo SET judul=:judul, isDone=:isDone WHERE id=:id")
    fun update(id:Long, judul:String, isDone:Boolean)
}