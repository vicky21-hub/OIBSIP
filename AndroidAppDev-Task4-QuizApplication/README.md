# Task 4 — Android Quiz Application

## OASIS INFOBYTE OCTOBER P1 INTERNSHIP 2026

---

## 1. Project Title
**QuizMaster — Android Multiple-Choice Quiz Application**

## 2. Oasis Infobyte Internship Details
- **Program:** Oasis Infobyte Android App Development Internship
- **Batch / Period:** October P1 2026
- **Task Number:** Task 4

## 3. Task Number
- **Task 4:** Quiz Application (Java / XML)

## 4. Project Overview
QuizMaster is a robust, lightweight, and fully offline native Android quiz application developed using Java and XML. It presents general knowledge questions one at a time, tracks user answers instantly with color-coded feedback, calculates scores dynamically, and provides a comprehensive results summary with personal best high score persistence.

## 5. Objectives
- Design and implement a clean, modern user interface following Material Design guidelines.
- Present multiple-choice quiz questions with dynamic question navigation and progress tracking.
- Provide instant visual and textual feedback on correct/incorrect answers.
- Persist personal best high scores using `SharedPreferences`.
- Implement robust local data storage (`QuizRepository`) and unit testing.

## 6. Features
- **Welcome Screen:** Features branding, personal best score tracking (`SharedPreferences`), and a Start Quiz button.
- **Dynamic Question Bank:** Contains 15 General Knowledge questions (shuffled on every attempt).
- **Interactive Quiz Flow:** One question at a time with a live question counter and progress bar.
- **Radio Button Options:** Four options per question using custom states.
- **Immediate Feedback:** Correct answers highlight green; selected wrong answers highlight red while the correct answer highlights green. Explanation text is displayed instantly.
- **Answer Lock:** Prevents modifying answers after selection.
- **Results Summary:** Displays final score, percentage, correct/incorrect count, performance feedback message, and option to restart or return home.
- **Offline Operation:** Fully self-contained with no network permissions or backend required.

## 7. Technologies Used
- **Language:** Java 17
- **UI Toolkit:** Android Views, XML Layouts, Material Components (`AppCompatActivity`)
- **Architecture:** MVVM Architecture (`ViewModel`, `ViewModelProvider`, `QuizRepository`)
- **Storage:** `SharedPreferences` (High score persistence)
- **Build System:** Gradle 9.x / Android Gradle Plugin 9.4.1
- **Testing:** JUnit 4 unit tests for ViewModel and Repository logic
- **Minimum SDK:** API 23 (Android 6.0)
- **Target / Compile SDK:** API 35 (Android 15)

## 8. Application Screenshots
*Screenshots of the Welcome, Quiz, and Result screens are stored in the [`screenshots/`](screenshots/) directory.*

## 9. Project Structure
```text
AndroidAppDev-Task4-QuizApplication/
├── app/
│   ├── build.gradle
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/oasisinfobyte/quizmaster/
│       │   │   ├── MainActivity.java
│       │   │   ├── QuizActivity.java
│       │   │   ├── ResultActivity.java
│       │   │   ├── QuizQuestion.java
│       │   │   ├── QuizRepository.java
│       │   │   └── QuizViewModel.java
│       │   └── res/
│       │       ├── drawable/       # Rounded cards, button & option state drawables
│       │       ├── layout/         # activity_main.xml, activity_quiz.xml, activity_result.xml
│       │       └── values/         # colors.xml, styles.xml
│       └── test/
│           └── java/com/oasisinfobyte/quizmaster/
│               ├── QuizRepositoryTest.java
│               └── QuizViewModelTest.java
├── docs/
│   ├── DEMO_SCRIPT.md
│   ├── PROJECT_DETAILS.md
│   └── TEST_CASES.md
├── screenshots/
├── build.gradle
│   ├── gradle.properties
│   ├── settings.gradle
│   ├── gradlew
│   └── gradlew.bat
```

## 10. Prerequisites
- **Android Studio:** Ladybug / Iguana or newer (supporting Gradle 9.x and JDK 17)
- **Java Development Kit (JDK):** Version 17
- **Android SDK:** Platform 35 and Build Tools 35

## 11. Android Studio Setup
1. Launch Android Studio.
2. Select **Open** and choose the `AndroidAppDev-Task4-QuizApplication` folder (the directory containing `settings.gradle`).
3. Ensure JDK 17 is selected in Project Structure settings.
4. Allow Gradle sync to complete.

## 12. Build and Run Instructions
- **Via Android Studio:**
  1. Select the `app` run configuration.
  2. Choose an emulator (API 23+) or connected Android device.
  3. Click the **Run** (Play) button.
- **Via Command Line (Gradle Wrapper):**
  ```bash
  cd AndroidAppDev-Task4-QuizApplication
  ./gradlew clean assembleDebug
  ```

## 13. Testing Details
The project includes automated unit tests verifying repository data loading and ViewModel state management.
- **Run Unit Tests via Gradle:**
  ```bash
  cd AndroidAppDev-Task4-QuizApplication
  ./gradlew testDebugUnitTest
  ```
- **Test Summary:** 4 passed, 0 failed.

## 14. Author Information
- **Intern:** vicky21-hub
- **GitHub Repository:** [vicky21-hub/OIBSIP](https://github.com/vicky21-hub/OIBSIP)
- **Program:** Oasis Infobyte Android App Development Internship (October 2026)
