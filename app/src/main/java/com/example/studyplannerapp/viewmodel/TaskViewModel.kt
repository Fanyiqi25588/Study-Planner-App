package com.example.studyplannerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyplannerapp.model.TaskEntity
import com.example.studyplannerapp.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    private val sortMode = MutableStateFlow("default")


    private fun sortedList(list: List<TaskEntity>): List<TaskEntity> {
        return when (sortMode.value) {
            "deadline" -> list.sortedBy { it.deadline ?: "9999-99-99" }
            else -> list.sortedByDescending { it.id }
        }
    }

    init {

        viewModelScope.launch {
            repository.getAllTasks().collect { list ->
                _tasks.value = sortedList(list)
            }
        }
    }


    fun setSortMode(mode: String) {
        sortMode.value = mode
        _tasks.value = sortedList(_tasks.value)
    }

    fun addTask(name: String, deadline: String?) {
        viewModelScope.launch {
            val task = TaskEntity(
                name = name,
                deadline = deadline
            )
            repository.insertTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun toggleTaskDone(task: TaskEntity) {
        viewModelScope.launch {
            val updated = task.copy(isDone = !task.isDone)
            repository.updateTask(updated)
        }
    }
}
