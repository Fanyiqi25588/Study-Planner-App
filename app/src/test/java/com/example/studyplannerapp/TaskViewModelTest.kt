package com.example.studyplannerapp

import com.example.studyplannerapp.model.TaskEntity
import com.example.studyplannerapp.repository.TaskRepositoryInterface
import com.example.studyplannerapp.viewmodel.TaskViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FakeTaskRepository : TaskRepositoryInterface {

    private val taskFlow = MutableStateFlow<List<TaskEntity>>(emptyList())

    override fun getAllTasks(): Flow<List<TaskEntity>> = taskFlow

    override suspend fun insertTask(task: TaskEntity) {
        taskFlow.value = taskFlow.value + task
    }

    override suspend fun deleteTask(task: TaskEntity) { }

    override suspend fun updateTask(task: TaskEntity) { }
}

class TaskViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @Test
    fun testAddTask() = runTest {

        val repo = FakeTaskRepository()
        val viewModel = TaskViewModel(repo, skipApi = true)

        viewModel.addTask("Math Homework", "2025-11-30")

        val tasks = repo.getAllTasks().first()

        assertEquals(1, tasks.size)
        assertEquals("Math Homework", tasks[0].name)
        assertEquals("2025-11-30", tasks[0].deadline)
    }
}
