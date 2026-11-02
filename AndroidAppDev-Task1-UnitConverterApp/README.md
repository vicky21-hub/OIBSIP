# Android App Development - Task 1: Unit Converter App

## Internship Details
* **Organization:** Oasis Infobyte (OIBSIP)
* **Internship Program:** Android App Development (October P1 Internship 2026)
* **Task Number:** Task 1

## Project Overview
The **Unit Converter App** is an intuitive and user-friendly Android application designed to convert values across various measurement categories effortlessly. It provides precise calculations with a clean Material Design UI.

## Objectives
* Build a functional and responsive Android application using Java and Android SDK.
* Implement multi-category conversion logic (Length, Weight, Temperature, Volume).
* Ensure proper input validation and user feedback via Toast messages and formatted results.
* Maintain clean architecture and adhere to modern Android development best practices.

## Features
* **Multi-Category Conversion:** Support for Length, Weight, Temperature, and Volume.
* **Dynamic Unit Selection:** Spinners automatically update source and target unit options based on the selected category.
* **Input Validation:** Handles empty inputs and invalid numeric entries gracefully.
* **Formatted Output:** Displays converted results formatted up to 6 decimal places with corresponding unit labels.
* **Material Design UI:** Modern layout using CardView, Spinner, and Material Design guidelines.

## Technologies Used
* **Language:** Java
* **UI Framework:** Android Views (XML), Material Components
* **Build System:** Gradle (AGP 8.9.3)
* **Testing:** JUnit, Espresso

## Screenshots
*(Place application screenshots in the `screenshots/` directory)*

## Project Structure
```
AndroidAppDev-Task1-UnitConverterApp/
├── app/                  # Application source code and resources
├── gradle/               # Gradle wrapper configuration
├── screenshots/          # Application screenshots
├── build.gradle          # Module/Project build configuration
├── settings.gradle       # Gradle settings
├── gradle.properties     # Gradle properties
├── gradlew               # Gradle wrapper (Linux/macOS)
└── gradlew.bat           # Gradle wrapper (Windows)
```

## Installation Requirements
* **Android Studio:** Ladybug / Jellyfish or newer
* **Gradle Version:** 9.3.0
* **Compile SDK:** 34
* **Min SDK:** 24 (Android 7.0+)
* **Target SDK:** 34

## Android Studio Setup
1. Clone or download the repository.
2. Open Android Studio.
3. Select **File > Open** and choose the `AndroidAppDev-Task1-UnitConverterApp` directory.
4. Allow Gradle to sync dependencies.

## How to Run
1. Connect an Android device or start an Android Emulator (API 24+).
2. Click the **Run** button (▶) in Android Studio or execute via terminal:
   ```bash
   gradlew.bat assembleDebug
   ```

## Testing Details
* **Unit Tests:** Located in `app/src/test/java/com/example/unitconverter/UnitConverterTest.java`.
* **Execution:**
  ```bash
  gradlew.bat testDebugUnitTest
  ```

## Author
* **Name:** Vicky
* **GitHub Username:** [vicky21-hub](https://github.com/vicky21-hub)
* **Repository:** [OIBSIP](https://github.com/vicky21-hub/OIBSIP)
