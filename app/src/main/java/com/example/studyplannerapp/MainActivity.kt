package com.example.studyplannerapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.studyplannerapp.model.TaskDatabase
import com.example.studyplannerapp.repository.TaskRepository
import com.example.studyplannerapp.ui.theme.StudyPlannerAppTheme
import com.example.studyplannerapp.ui_screens.AddTaskScreen
import com.example.studyplannerapp.ui_screens.HomeScreen
import com.example.studyplannerapp.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        createNotificationChannel()

        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "task_database"
        )
            .fallbackToDestructiveMigration()
            .build()


        val repository = TaskRepository(db.taskDao())
        val taskViewModel = TaskViewModel(repository)

        setContent {
            StudyPlannerAppTheme {
                StudyPlannerApp(taskViewModel, this)
            }
        }
    }

    private fun createNotificationChannel() {
        val channelId = "task_channel"
        val channelName = "Task Reminders"
        val channelDescription = "Notifications for study tasks"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(channelId, channelName, importance).apply {
                    description = channelDescription
                }

            val notificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun sendTaskNotification(taskName: String, deadline: String?) {
        val channelId = "task_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            val hasPermission = androidx.core.content.ContextCompat.checkSelfPermission(this, permission)

            if (hasPermission != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                return
            }
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setContentTitle("New Task Added")
            .setContentText("Task: $taskName" + (deadline?.let { " (Due: $it)" } ?: ""))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}

@Composable
fun StudyPlannerApp(taskViewModel: TaskViewModel, activity: MainActivity) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onAddTaskClick = { navController.navigate("addTask") },
                viewModel = taskViewModel
            )
        }

        composable("addTask") {
            AddTaskScreen(
                onSaveTask = { title, deadline ->
                    taskViewModel.addTask(title, deadline)
                    activity.sendTaskNotification(title, deadline)
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
