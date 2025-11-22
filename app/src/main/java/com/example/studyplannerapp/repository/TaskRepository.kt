package com.example.studyplannerapp.repository

import com.example.studyplannerapp.model.TaskDao
import com.example.studyplannerapp.model.TaskEntity

class TaskRepository(private val dao: TaskDao) {

    suspend fun updateTask(task: TaskEntity) {
        dao.updateTask(task)
    }

    suspend fun insertTask(task: TaskEntity) {
        dao.insertTask(task)
    }
    suspend fun deleteTask(task: TaskEntity) {
        dao.deleteTask(task)
    }

    fun getAllTasks() = dao.getAllTasks()
}
