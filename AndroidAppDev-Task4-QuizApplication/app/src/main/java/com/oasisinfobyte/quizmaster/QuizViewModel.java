package com.oasisinfobyte.quizmaster;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuizViewModel extends ViewModel {
    private final List<QuizQuestion> questions;
    private int currentIndex = 0;
    private int score = 0;
    private boolean answered = false;
    private int lastSelectedIndex = -1;

    public QuizViewModel() {
        questions = QuizRepository.getQuestions();
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public QuizQuestion getCurrentQuestion() {
        if (currentIndex >= 0 && currentIndex < questions.size()) {
            return questions.get(currentIndex);
        }
        return null;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getScore() {
        return score;
    }

    public boolean isAnswered() {
        return answered;
    }

    public int getLastSelectedIndex() {
        return lastSelectedIndex;
    }

    public boolean checkAnswer(int selectedIndex) {
        if (answered) return false;
        answered = true;
        lastSelectedIndex = selectedIndex;
        QuizQuestion q = getCurrentQuestion();
        if (q != null && selectedIndex == q.getCorrectAnswerIndex()) {
            score++;
            return true;
        }
        return false;
    }

    public boolean nextQuestion() {
        currentIndex++;
        answered = false;
        lastSelectedIndex = -1;
        return currentIndex < questions.size();
    }

    public void restart() {
        currentIndex = 0;
        score = 0;
        answered = false;
        lastSelectedIndex = -1;
    }
}
