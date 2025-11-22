package com.example.studyplannerapp.repository

import com.example.studyplannerapp.model.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepositoryInterface {
    fun getAllTasks(): Flow<List<TaskEntity>>
    suspend fun insertTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
    suspend fun updateTask(task: TaskEntity)
}

