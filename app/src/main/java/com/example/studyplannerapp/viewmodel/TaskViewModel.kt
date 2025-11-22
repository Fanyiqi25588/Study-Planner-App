package com.example.studyplannerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyplannerapp.model.TaskEntity
import com.example.studyplannerapp.repository.TaskRepository
import com.example.studyplannerapp.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    var quoteText = MutableStateFlow("Loading inspiring quote...")

    var sortMode = MutableStateFlow("default")


    private fun applySort(list: List<TaskEntity>, mode: String): List<TaskEntity> {
        return when (mode) {
            "deadline" -> list.sortedBy { it.deadline ?: "9999-99-99" }
            else -> list.sortedByDescending { it.id }
        }
    }


    init {
        loadQuote()

        viewModelScope.launch {
            repository.getAllTasks().collect { list ->
                _tasks.value = applySort(list, sortMode.value)
            }
        }
    }


    fun loadQuote() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getRandomQuote()
                val quote = response[0]

                quoteText.value = "\"${quote.q}\" — ${quote.a}"
            } catch (e: Exception) {
                quoteText.value = "Failed to load quote."
            }
        }
    }

    fun setSortMode(mode: String) {
        sortMode.value = mode
        _tasks.value = applySort(_tasks.value, mode)
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
