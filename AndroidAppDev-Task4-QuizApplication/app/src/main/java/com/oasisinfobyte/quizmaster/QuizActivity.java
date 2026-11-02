package com.oasisinfobyte.quizmaster;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class QuizActivity extends AppCompatActivity {
    private QuizViewModel viewModel;

    private TextView tvCounter, tvQuestion, tvFeedback;
    private ProgressBar progressBar;
    private RadioGroup optionsGroup;
    private RadioButton[] optionButtons;
    private Button btnNext;

    private final int green = Color.rgb(22, 130, 82);
    private final int red = Color.rgb(190, 55, 55);
    private final int dark = Color.rgb(31, 41, 55);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        tvCounter = findViewById(R.id.tvCounter);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvFeedback = findViewById(R.id.tvFeedback);
        progressBar = findViewById(R.id.progressBar);
        optionsGroup = findViewById(R.id.optionsGroup);
        btnNext = findViewById(R.id.btnNext);

        optionButtons = new RadioButton[] {
                findViewById(R.id.optionA), findViewById(R.id.optionB),
                findViewById(R.id.optionC), findViewById(R.id.optionD)
        };

        for (RadioButton button : optionButtons) {
            button.setOnClickListener(v -> checkAnswer((RadioButton) v));
        }

        btnNext.setOnClickListener(v -> {
            if (!viewModel.isAnswered()) {
                Toast.makeText(this, "Please select an answer first.", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean hasMore = viewModel.nextQuestion();
            if (hasMore) {
                showQuestion();
            } else {
                openResults();
            }
        });

        showQuestion();
        if (viewModel.isAnswered()) {
            restoreAnswerState();
        }
    }

    private void showQuestion() {
        QuizQuestion question = viewModel.getCurrentQuestion();
        if (question == null) return;

        tvCounter.setText("QUESTION " + (viewModel.getCurrentIndex() + 1) + " OF " + viewModel.getTotalQuestions());
        tvQuestion.setText(question.getQuestion());
        progressBar.setMax(viewModel.getTotalQuestions());
        progressBar.setProgress(viewModel.getCurrentIndex() + 1);
        tvFeedback.setVisibility(View.GONE);
        optionsGroup.clearCheck();

        String[] options = question.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            RadioButton button = optionButtons[i];
            button.setText(options[i]);
            button.setEnabled(true);
            button.setTextColor(dark);
            button.setTypeface(null, Typeface.NORMAL);
            button.setBackgroundResource(R.drawable.bg_option);
        }
        btnNext.setText(viewModel.getCurrentIndex() == viewModel.getTotalQuestions() - 1 ? "See Results" : "Next Question");
    }

    private void restoreAnswerState() {
        int selectedIndex = viewModel.getLastSelectedIndex();
        QuizQuestion question = viewModel.getCurrentQuestion();
        int correctIndex = question.getCorrectAnswerIndex();

        if (selectedIndex >= 0 && selectedIndex < optionButtons.length) {
            RadioButton selectedButton = optionButtons[selectedIndex];
            selectedButton.setChecked(true);
            if (selectedIndex == correctIndex) {
                selectedButton.setBackgroundResource(R.drawable.bg_option_correct);
                selectedButton.setTextColor(green);
                tvFeedback.setText("Correct! " + question.getExplanation());
                tvFeedback.setTextColor(green);
            } else {
                selectedButton.setBackgroundResource(R.drawable.bg_option_wrong);
                selectedButton.setTextColor(red);
                RadioButton correctButton = optionButtons[correctIndex];
                correctButton.setBackgroundResource(R.drawable.bg_option_correct);
                correctButton.setTextColor(green);
                tvFeedback.setText("Not quite. " + question.getExplanation());
                tvFeedback.setTextColor(red);
            }
        }
        for (RadioButton button : optionButtons) button.setEnabled(false);
        tvFeedback.setVisibility(View.VISIBLE);
    }

    private void checkAnswer(RadioButton selectedButton) {
        if (viewModel.isAnswered()) return;

        int selectedIndex = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].getId() == selectedButton.getId()) {
                selectedIndex = i;
                break;
            }
        }
        if (selectedIndex < 0) return;

        boolean isCorrect = viewModel.checkAnswer(selectedIndex);
        QuizQuestion question = viewModel.getCurrentQuestion();
        int correctIndex = question.getCorrectAnswerIndex();

        if (isCorrect) {
            selectedButton.setBackgroundResource(R.drawable.bg_option_correct);
            selectedButton.setTextColor(green);
            tvFeedback.setText("Correct! " + question.getExplanation());
            tvFeedback.setTextColor(green);
        } else {
            selectedButton.setBackgroundResource(R.drawable.bg_option_wrong);
            selectedButton.setTextColor(red);
            RadioButton correctButton = optionButtons[correctIndex];
            correctButton.setBackgroundResource(R.drawable.bg_option_correct);
            correctButton.setTextColor(green);
            tvFeedback.setText("Not quite. " + question.getExplanation());
            tvFeedback.setTextColor(red);
        }

        for (RadioButton button : optionButtons) button.setEnabled(false);
        tvFeedback.setVisibility(View.VISIBLE);
    }

    private void openResults() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_SCORE, viewModel.getScore());
        intent.putExtra(ResultActivity.EXTRA_TOTAL, viewModel.getTotalQuestions());
        startActivity(intent);
        finish();
    }
}
