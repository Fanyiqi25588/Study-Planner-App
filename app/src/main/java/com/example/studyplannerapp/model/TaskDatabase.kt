package com.example.studyplannerapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 3)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
