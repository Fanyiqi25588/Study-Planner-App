Study Planner App

A Mobile App for Managing Study Tasks — CP3406 Assignment 2

The Study Planner App helps users manage tasks, set deadlines, track progress, and organise their study schedules.
The app is built using Kotlin, Jetpack Compose, Room Database, and MVVM architecture, showcasing modern Android development practices.

✨ Features

Add Study Tasks.
Enter task name.
Choose a deadline using Material Design DatePicker.

📝 Task List

View all tasks in a clean Material 3 UI.
Each task is displayed using a styled Card.
Room database ensures tasks are saved permanently.

✔ Mark Tasks as Completed

Tap to toggle done / not done.
Real-time UI update using StateFlow.

🗑 Delete Tasks

Remove tasks instantly.
UI auto-updates without refresh.

🔃 Sorting Options

Sort tasks by:
Newest first (default).
Deadline (earliest first).

🎨 Modern UI (Jetpack Compose)

Material 3 components.
Floating Action Button.
TopAppBar with actions.
Custom color theme.

🛠 Tech Stack

Frontend.
Jetpack Compose.
Material 3 UI.
Navigation Compose.

Architecture

MVVM.
StateFlow & MutableStateFlow.
Coroutines.
Repository Pattern.

Database

Room.
DAO.
Entity.
Flow for live database updates.


app/src/main/java/com.example.studyplannerapp/
│
├── model
│   ├── TaskEntity.kt
│   ├── TaskDao.kt
│   └── TaskDatabase.kt
│
├── repository
│   └── TaskRepository.kt
│
├── viewmodel
│   └── TaskViewModel.kt
│
├── ui_screens
│   ├── HomeScreen.kt
│   └── AddTaskScreen.kt
│
└── MainActivity.kt



🚀 How to Run

1.Clone this repository

2.Open the project in Android Studio

3.Ensure the following:
Android SDK 34 installed
Kotlin plugin enabled
Emulator or physical Android device

4.Run the project using ▶️ Run button

5.Tasks persist automatically using Room

🔮 Future Improvements

Edit Task screen

Notification reminders for deadlines

Online sync (cloud backup)

Dark mode / dynamic colors

Animated transitions

More advanced task filtering

