# Stopwatch Application (Android App Development - Task 2)

An intuitive, feature-rich Stopwatch Android application built with **Java**, **XML layouts**, and **Jetpack** libraries as part of the OASIS INFOBYTE Android App Development Internship.

## 📱 Features & Functionality
- **Large Time Display:** High-precision elapsed time in `MM:SS.ms` and `HH:MM:SS.ms` formats.
- **Start / Resume:** Begins timer from zero or resumes from paused state.
- **Pause / Stop:** Freezes timer at current elapsed time.
- **Reset:** Clears elapsed time, resets display to `00:00:00`, clears lap history, and restores initial button states.
- **Lap Recording:** Records split lap times into a scrollable `RecyclerView` list.
- **Lifecycle & Configuration Persistence:** Robust handling of `onSaveInstanceState` and `configChanges` (orientation/screen rotation) preventing time drift or state loss.
- **State-aware Buttons:** Dynamic button enabling/disabling and alpha transitions.

## 🛠️ Tech Stack & Architecture
- **Language:** Java 17
- **UI Toolkit:** Android Views, XML Constraints (`ConstraintLayout`), Material Design 3
- **Threading:** `Handler` and `Runnable` posting updates every 50ms with `SystemClock.elapsedRealtime()`
- **Build System:** Gradle 8.7, Android Gradle Plugin (AGP) 8.5.0
- **Testing:** JUnit (Unit Tests) & Espresso (Instrumented UI Tests)

## 🚀 Getting Started & Testing
1. Open the project folder `AndroidAppDev-Task2-Stopwatch/` in Android Studio.
2. Allow Gradle sync to complete.
3. Run unit tests via `./gradlew testDebugUnitTest` or execute the app on an Android emulator/device.
