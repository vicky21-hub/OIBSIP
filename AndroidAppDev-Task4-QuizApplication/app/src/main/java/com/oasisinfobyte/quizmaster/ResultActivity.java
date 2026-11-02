package com.oasisinfobyte.quizmaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {
    public static final String EXTRA_SCORE = "extra_score";
    public static final String EXTRA_TOTAL = "extra_total";
    private static final String PREF_NAME = "quiz_prefs";
    private static final String KEY_HIGH_SCORE = "high_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra(EXTRA_SCORE, 0);
        int total = getIntent().getIntExtra(EXTRA_TOTAL, 0);
        int incorrect = Math.max(0, total - score);
        int percentage = total == 0 ? 0 : Math.round((score * 100f) / total);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int highScore = prefs.getInt(KEY_HIGH_SCORE, 0);
        boolean isNewHighScore = score > highScore;
        if (isNewHighScore) {
            highScore = score;
            prefs.edit().putInt(KEY_HIGH_SCORE, highScore).apply();
        }

        TextView tvPercentage = findViewById(R.id.tvPercentage);
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvCorrect = findViewById(R.id.tvCorrect);
        TextView tvIncorrect = findViewById(R.id.tvIncorrect);
        TextView tvMessage = findViewById(R.id.tvMessage);
        Button btnRestart = findViewById(R.id.btnRestart);
        Button btnHome = findViewById(R.id.btnHome);

        tvPercentage.setText(percentage + "%");
        tvScore.setText(score + " / " + total + (isNewHighScore ? " (New Best! 🏆)" : " (Best: " + highScore + ")"));
        tvCorrect.setText(String.valueOf(score));
        tvIncorrect.setText(String.valueOf(incorrect));

        if (percentage >= 80) tvMessage.setText("Excellent work! You have a strong score.");
        else if (percentage >= 50) tvMessage.setText("Good effort! Keep practising to improve.");
        else tvMessage.setText("Keep learning. Try again and beat your score!");

        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            startActivity(intent);
            finish();
        });
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
