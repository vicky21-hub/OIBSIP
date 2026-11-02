# Project Details — QuizMaster

## 1. Project overview
QuizMaster is an offline Android quiz application for testing general knowledge. It provides a simple three-stage experience: welcome, question flow, and results. The app is implemented natively with Java and XML, matching the OASIS INFOBYTE Task 4 technology requirements.

## 2. Problem statement
A learner needs a lightweight way to practise multiple-choice questions and understand mistakes without requiring an account or internet connection.

## 3. Objectives
- Present a clear, mobile-friendly quiz flow.
- Give immediate visual feedback for each selected answer.
- Track correct and incorrect answers accurately.
- Show a useful summary at the end.
- Keep question data local and easy to maintain.

## 4. Functional requirements
| ID | Requirement | Implementation |
|---|---|---|
| FR-01 | Welcome screen | `MainActivity` + `activity_main.xml` |
| FR-02 | Start quiz | Start button launches `QuizActivity` |
| FR-03 | At least 10 questions | 15 questions in `QuizRepository` |
| FR-04 | Four options per question | Four `RadioButton` views in `RadioGroup` |
| FR-05 | Question counter | `tvCounter` |
| FR-06 | Progress indication | Horizontal `ProgressBar` |
| FR-07 | Immediate correct/wrong feedback | `checkAnswer()` |
| FR-08 | Correct answer highlighted green | `bg_option_correct.xml` |
| FR-09 | Incorrect selection highlighted red | `bg_option_wrong.xml` |
| FR-10 | Next question navigation | `btnNext` |
| FR-11 | Score tracking | `score` field in `QuizActivity` |
| FR-12 | Results summary | `ResultActivity` |
| FR-13 | Restart | Launches a new quiz attempt |
| FR-14 | Shuffle questions | `Collections.shuffle()` in repository |

## 5. Non-functional requirements
- Usable on Android API 23 and later.
- No internet permission or remote service dependency.
- Clear contrast and readable text.
- Separation between question data, quiz flow, and results.
- Standard Android components; no third-party library required.

## 6. Application flow
1. User opens the app and sees the welcome screen.
2. User taps Start Quiz.
3. The app loads and shuffles the local question list.
4. The first question and four choices appear.
5. User selects one answer.
6. The app locks the choices, highlights the result, and displays an explanation.
7. User taps Next Question to continue.
8. After the final question, the app opens the result screen.
9. User can restart or return to the welcome screen.

## 7. Score calculation
- Correct answers = number of selections matching the stored correct answer index.
- Incorrect answers = total questions − correct answers.
- Percentage = `(correct answers × 100) / total questions`, rounded to the nearest whole number.

There is no negative marking and unanswered questions cannot be skipped because the Next button requires an answer.

## 8. Code responsibilities
- `QuizQuestion.java`: immutable data model for a question.
- `QuizRepository.java`: local question bank and random ordering.
- `MainActivity.java`: welcome screen and navigation.
- `QuizActivity.java`: displays questions, validates selections, feedback, and score.
- `ResultActivity.java`: calculates and displays final statistics.

## 9. Current scope and limitations
- One built-in General Knowledge question set.
- No login, leaderboard, database, timer, categories, or online question API.
- Results are not persisted after the app is closed.
- Question order is shuffled; answer-option order is fixed.
- This is a functional internship task implementation, not a production examination platform.

## 10. Future enhancements
- Add selectable categories and difficulty levels.
- Load question banks from local JSON.
- Add an optional countdown timer.
- Store best scores using SharedPreferences or Room.
- Add review mode showing all user answers.
- Add accessibility improvements and localisation.
