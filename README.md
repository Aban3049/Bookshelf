📚 E-Book App

This repository contains an E-Book App developed using Compose Multiplatform, designed to work seamlessly across Android, iOS, and Desktop platforms. The app was created as part of the Compose Multiplatform crash course by Philipp Lackner, showcasing the power of Kotlin Multiplatform for building modern cross-platform applications.
🚀 Features

    Cross-Platform Support: A single codebase for Android, iOS, and Desktop.
    MVI Architecture: Ensures clean, scalable, and maintainable code.
    API Integration: Powered by Ktor for making seamless network requests.
    Local Storage: Utilizes Room Database for efficient offline storage.
    State Management: Optimal use of states and ViewModels for reactive UIs.
    Animations: Engaging and dynamic UI interactions for better user experience.

🛠️ Tech Stack

    Compose Multiplatform: For building declarative UIs across platforms.
    Kotlin: The primary programming language for logic and UI.
    MVI Architecture: For predictable state flow and separation of concerns.
    Ktor: Lightweight HTTP client for API calls.
    Koin: Dependency injection for better modularization.
    Room Database: Local database for persistent storage.

📂 Project Structure

📦 e-book-app  
├── 📂 androidApp       // Android-specific configurations  
├── 📂 iosApp           // iOS-specific configurations  
├── 📂 desktopApp       // Desktop-specific configurations  
├── 📂 shared            // Shared business logic and UI  
│   ├── 📂 data          // Data layer (repositories, models, etc.)  
│   ├── 📂 ui            // Compose-based UI  
│   ├── 📂 di            // Dependency injection (Koin modules)  
│   └── 📂 utils         // Utility classes and helpers  

🖥️ Screenshots


🤝 Contributing

Feel free to fork this repository, submit issues, or create pull requests. Any contributions to enhance the app are highly appreciated!

🌟 Acknowledgments

A big thank you to Philipp Lackner for creating such an amazing crash course that made this project possible! 🎉
