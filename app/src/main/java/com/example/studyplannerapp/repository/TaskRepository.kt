package com.example.studyplannerapp.repository

import com.example.studyplannerapp.model.TaskDao
import com.example.studyplannerapp.model.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) : TaskRepositoryInterface {

    override fun getAllTasks(): Flow<List<TaskEntity>> = dao.getAllTasks()

    override suspend fun insertTask(task: TaskEntity) = dao.insertTask(task)

    override suspend fun deleteTask(task: TaskEntity) = dao.deleteTask(task)

    override suspend fun updateTask(task: TaskEntity) = dao.updateTask(task)
}

