# Test Cases — QuizMaster

| Test ID | Scenario | Steps | Expected result |
|---|---|---|---|
| TC-01 | Launch app | Open QuizMaster | Welcome screen appears |
| TC-02 | Start quiz | Tap Start Quiz | First question appears |
| TC-03 | Question count | Start a quiz and inspect counter | Counter starts at 1 of 15 |
| TC-04 | Four options | Inspect a question | Exactly four choices are visible |
| TC-05 | Correct answer | Select the correct option | Selected correct option turns green; explanation appears |
| TC-06 | Incorrect answer | Select a wrong option | Selected option turns red; correct option turns green |
| TC-07 | Lock answer | Select an option, then try another | Further answer changes are disabled |
| TC-08 | Required answer | Tap Next without selecting | Toast asks user to select an answer; question does not advance |
| TC-09 | Progress | Answer and advance | Counter and progress bar advance |
| TC-10 | Score | Answer a known mix of correct and wrong answers | Score increments only for correct answers |
| TC-11 | Final results | Answer all questions | Results screen shows score, correct, incorrect, percentage |
| TC-12 | Restart | Tap Restart Quiz | New attempt begins and score resets |
| TC-13 | Return home | Tap Back to Home | Welcome screen appears |
| TC-14 | Offline mode | Disable network and run app | Quiz works without network |
| TC-15 | Shuffle | Start multiple attempts | Question order is shuffled (random order may occasionally repeat) |

## Test record
Record actual device/emulator, Android version, date, pass/fail, and notes after executing each test. Do not mark tests as passed until they have been run.
