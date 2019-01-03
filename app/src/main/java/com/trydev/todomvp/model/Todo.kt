package com.trydev.todomvp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long = 0,

    @ColumnInfo(name = "judul")
    var judul:String = "",

    @ColumnInfo(name = "isDone")
    var isDone:Boolean = false
)