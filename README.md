# 📚 Study Planner App  
*A simple and smart study planning app built with Kotlin + Jetpack Compose.*

---

🌟 Overview  
Study Planner App is a mobile application designed to help students organise their study tasks, set deadlines, and stay motivated with daily inspirational quotes.  
This app was developed for **CP3406 – Mobile App Development (Assessment 2)** using modern Android development tools.

---

✨ Features

  **Task Management**
- Add new study tasks  
- Include deadlines (Date Picker)  
- Mark tasks as completed  
- Delete tasks  
- Tasks are stored locally using **Room Database** (persistent storage)

🗂 **Sorting Options**
- Sort by newest  
- Sort by deadline  

🔔 **Notifications**
- Sends a notification when a new task is added  
- Android 13+ permission handling included

🌐 **External API Integration**
- Fetches inspirational study quotes using **Retrofit + JSON API**
- Quote updates on app startup

🎨 **Modern UI with Material Design 3**
- Clean Home screen with Card layout  
- Floating Action Button（+）  
- Light theme with customised colours  
- Responsive Compose layouts

📦 **Architecture (MVVM + Repository)**
- ViewModel with Flow  
- Repository abstraction  
- Room DAO for local storage  
- Retrofit for networking  
- Separations: `ui_screens`, `viewmodel`, `model`, `repository`

---

🛠 Tech Stack

| Category | Technologies |
|---------|--------------|
| **Language** | Kotlin |
| **UI** | Jetpack Compose + Material 3 |
| **Local Storage** | Room Database |
| **Networking** | Retrofit2 + Gson |
| **Architecture** | MVVM, Repository |
| **Async** | Coroutines + Flow |
| **Notifications** | NotificationChannel + ManagerCompat |
| **Version Control** | Git + GitHub |






