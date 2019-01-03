package com.trydev.todomvp.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoDatabase:RoomDatabase() {

    abstract fun todoDao():TodoDao
    companion object {
        private var INSTANCE:TodoDatabase? =null

        fun getInstance(context: Context):TodoDatabase?{
            if (INSTANCE==null){
                synchronized(TodoDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TodoDatabase::class.java, "tododata.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}