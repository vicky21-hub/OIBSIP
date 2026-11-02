package com.oasisinfobyte.quizmaster;

public class QuizQuestion {
    private final String question;
    private final String[] options;
    private final int correctAnswerIndex;
    private final String explanation;

    public QuizQuestion(String question, String[] options, int correctAnswerIndex, String explanation) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.explanation = explanation;
    }

    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    public String getExplanation() { return explanation; }
}
