package com.oasisinfobyte.quizmaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String PREF_NAME = "quiz_prefs";
    private static final String KEY_HIGH_SCORE = "high_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int highScore = prefs.getInt(KEY_HIGH_SCORE, 0);

        TextView tvHighScore = findViewById(R.id.tvHighScore);
        if (tvHighScore != null) {
            tvHighScore.setText("Personal Best: " + highScore + " / 15");
        }

        Button startButton = findViewById(R.id.btnStart);
        startButton.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, QuizActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int highScore = prefs.getInt(KEY_HIGH_SCORE, 0);
        TextView tvHighScore = findViewById(R.id.tvHighScore);
        if (tvHighScore != null) {
            tvHighScore.setText("Personal Best: " + highScore + " / 15");
        }
    }
}
