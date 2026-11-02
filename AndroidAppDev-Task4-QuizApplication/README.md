# QuizMaster — OASIS INFOBYTE Android App Development (Task 4)

A native Android multiple-choice quiz application built with **Java and XML**. It presents one question at a time, gives instant answer feedback, tracks the score, and displays a results summary.

> Internship task brief: Task 4 — Quiz Application. This repository is a starter implementation intended to be opened, tested, personalised, and submitted by the intern.

## Features

- Welcome screen with a clear Start Quiz action.
- 15 locally stored General Knowledge questions (requirement: at least 10).
- One question displayed at a time with a question counter and progress bar.
- Four answer choices using `RadioButton` controls inside a `RadioGroup`.
- Immediate feedback: correct option is highlighted green; a selected wrong option is highlighted red and the correct option green.
- Explanation displayed after every answer.
- Prevents changing an answer after feedback is shown.
- Next Question / See Results navigation.
- Final score, correct count, incorrect count, percentage, and performance message.
- Restart Quiz and Back to Home actions.
- Question order shuffled at the start of every attempt.
- Offline operation; no API key, internet permission, or backend required.

## Technology

- Android Studio
- Java 17
- XML layouts and Android framework widgets
- Minimum Android version: API 23 (Android 6.0)
- Compile / target SDK: 35

## Project structure

```text
QuizMaster/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/oasisinfobyte/quizmaster/
│       │   ├── MainActivity.java
│       │   ├── QuizActivity.java
│       │   ├── ResultActivity.java
│       │   ├── QuizQuestion.java
│       │   └── QuizRepository.java
│       └── res/
│           ├── drawable/       # Rounded cards, buttons, answer states
│           ├── layout/         # Welcome, quiz, and result screens
│           └── values/         # Colours and styles
├── docs/
│   ├── PROJECT_DETAILS.md
│   ├── TEST_CASES.md
│   └── DEMO_SCRIPT.md
├── build.gradle
├── gradle.properties
└── settings.gradle
```

## Open and run in Android Studio

1. Extract the ZIP file.
2. Open Android Studio → **Open** → select the `QuizMaster` folder (the folder containing `settings.gradle`).
3. Allow Gradle sync to finish. Use JDK 17.
4. If Android Studio asks to install Android SDK Platform 35, install it using SDK Manager.
5. Create an emulator (API 23 or later) or connect an Android phone with USB debugging enabled.
6. Select the `app` run configuration and click **Run**.

The first Gradle sync requires an internet connection to download the Android Gradle Plugin and SDK components. The application itself runs offline.

## Customise questions

Edit `QuizRepository.java`. Each question contains:
- Question text
- Four answer strings
- Correct answer index (`0` = first option, `1` = second, etc.)
- Explanation shown after the answer

Keep exactly four options per question and ensure the correct index is between 0 and 3.

## Important implementation notes

- `QuizRepository.getQuestions()` creates a fresh list and shuffles it for each attempt.
- `QuizActivity` owns the quiz state and score calculation.
- `ResultActivity` receives score and total question count using Intent extras.
- All question content is bundled locally, so no network permission is required.

## Before internship submission

- Replace the placeholder project identity in screenshots/video with your own name where required.
- Build and run on an emulator or physical Android phone.
- Test every feature and fix any device-specific issues.
- Capture genuine screenshots and a screen-recorded demo.
- Add your own name, internship track, and task title to the first 2 seconds of the demo video.
- Push the project to the required GitHub repository named exactly `OBISIP` (per the supplied internship instructions). Keep this project in its own task folder, following the folder naming format specified by your internship.
- Submit through the official form only. Include the GitHub repository link and live demo if applicable.
- Do not claim evaluation, certification, or completion until the organisation confirms it.

## Author

Add your name, college, and internship details here before publishing.
