package com.example.studyplannerapp.ui_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.studyplannerapp.MainActivity
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onSaveTask: (String, String?) -> Unit,
    onBack: () -> Unit
) {
    var taskText by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf<String?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Study Task") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = taskText,
                onValueChange = { taskText = it },
                label = { Text("Task Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { showDatePicker = true }) {
                Text("Select Deadline")
            }

            deadline?.let {
                Text(
                    text = "Deadline: $it",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onSaveTask(taskText, deadline)
                    (context as MainActivity).sendTaskNotification(taskText, deadline)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Task")
            }

        }
    }


    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(
                    onClick = {
                        val dateMillis = datePickerState.selectedDateMillis
                        if (dateMillis != null) {
                            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            deadline = formatter.format(Date(dateMillis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
