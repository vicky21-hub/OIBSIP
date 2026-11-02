package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimerDisplay;
    private Button btnStart, btnPause, btnReset, btnLap;
    private RecyclerView recyclerViewLaps;

    private Handler handler;
    private Runnable updateTimerRunnable;

    private long startTime = 0L;
    private long timeSwapBuff = 0L;
    private long updateTime = 0L;
    private long totalElapsedMillis = 0L;

    private boolean isRunning = false;
    private boolean isPaused = false;

    private final List<LapAdapter.LapItem> lapList = new ArrayList<>();
    private LapAdapter lapAdapter;
    private int lapCounter = 1;

    private static final String KEY_TOTAL_ELAPSED = "key_total_elapsed";
    private static final String KEY_IS_RUNNING = "key_is_running";
    private static final String KEY_IS_PAUSED = "key_is_paused";
    private static final String KEY_START_TIME = "key_start_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimerDisplay = findViewById(R.id.tvTimerDisplay);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);
        btnLap = findViewById(R.id.btnLap);
        recyclerViewLaps = findViewById(R.id.recyclerViewLaps);

        lapAdapter = new LapAdapter(lapList);
        recyclerViewLaps.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLaps.setAdapter(lapAdapter);

        handler = new Handler(Looper.getMainLooper());

        updateTimerRunnable = new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    totalElapsedMillis = timeSwapBuff + (SystemClock.elapsedRealtime() - startTime);
                    tvTimerDisplay.setText(formatTime(totalElapsedMillis));
                    handler.postDelayed(this, 50); // update every 50ms
                }
            }
        };

        if (savedInstanceState != null) {
            totalElapsedMillis = savedInstanceState.getLong(KEY_TOTAL_ELAPSED, 0L);
            isRunning = savedInstanceState.getBoolean(KEY_IS_RUNNING, false);
            isPaused = savedInstanceState.getBoolean(KEY_IS_PAUSED, false);
            startTime = savedInstanceState.getLong(KEY_START_TIME, 0L);
            if (isRunning) {
                timeSwapBuff = totalElapsedMillis - (SystemClock.elapsedRealtime() - startTime);
                handler.post(updateTimerRunnable);
            } else {
                timeSwapBuff = totalElapsedMillis;
            }
            tvTimerDisplay.setText(formatTime(totalElapsedMillis));
        }

        updateButtonStates();

        btnStart.setOnClickListener(v -> startStopwatch());
        btnPause.setOnClickListener(v -> pauseStopwatch());
        btnReset.setOnClickListener(v -> resetStopwatch());
        btnLap.setOnClickListener(v -> recordLap());
    }

    private void startStopwatch() {
        if (!isRunning) {
            startTime = SystemClock.elapsedRealtime();
            handler.post(updateTimerRunnable);
            isRunning = true;
            isPaused = false;
            updateButtonStates();
        }
    }

    private void pauseStopwatch() {
        if (isRunning) {
            timeSwapBuff += SystemClock.elapsedRealtime() - startTime;
            handler.removeCallbacks(updateTimerRunnable);
            isRunning = false;
            isPaused = true;
            updateButtonStates();
        }
    }

    private void resetStopwatch() {
        handler.removeCallbacks(updateTimerRunnable);
        isRunning = false;
        isPaused = false;
        startTime = 0L;
        timeSwapBuff = 0L;
        totalElapsedMillis = 0L;
        tvTimerDisplay.setText(getString(R.string.time_zero));
        lapList.clear();
        lapCounter = 1;
        lapAdapter.notifyDataSetChanged();
        updateButtonStates();
    }

    private void recordLap() {
        if (isRunning || isPaused) {
            String currentTimeStr = formatTime(totalElapsedMillis);
            lapList.add(0, new LapAdapter.LapItem("Lap " + lapCounter++, currentTimeStr));
            lapAdapter.notifyItemInserted(0);
            recyclerViewLaps.scrollToPosition(0);
        }
    }

    private void updateButtonStates() {
        if (isRunning) {
            btnStart.setEnabled(false);
            btnStart.setAlpha(0.5f);
            btnPause.setEnabled(true);
            btnPause.setAlpha(1.0f);
            btnPause.setText(getString(R.string.pause));
            btnReset.setEnabled(true);
            btnReset.setAlpha(1.0f);
            btnLap.setEnabled(true);
            btnLap.setAlpha(1.0f);
        } else if (isPaused) {
            btnStart.setEnabled(true);
            btnStart.setAlpha(1.0f);
            btnStart.setText(getString(R.string.resume));
            btnPause.setEnabled(false);
            btnPause.setAlpha(0.5f);
            btnReset.setEnabled(true);
            btnReset.setAlpha(1.0f);
            btnLap.setEnabled(false);
            btnLap.setAlpha(0.5f);
        } else {
            // Initial / Reset state
            btnStart.setEnabled(true);
            btnStart.setAlpha(1.0f);
            btnStart.setText(getString(R.string.start));
            btnPause.setEnabled(false);
            btnPause.setAlpha(0.5f);
            btnReset.setEnabled(false);
            btnReset.setAlpha(0.5f);
            btnLap.setEnabled(false);
            btnLap.setAlpha(0.5f);
        }
    }

    private String formatTime(long millis) {
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        int centiseconds = (int) ((millis % 1000) / 10);

        if (hours > 0) {
            return String.format(Locale.getDefault(), "%02d:%02d:%02d.%02d", hours, minutes, seconds, centiseconds);
        } else {
            return String.format(Locale.getDefault(), "%02d:%02d.%02d", minutes, seconds, centiseconds);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRunning) {
            totalElapsedMillis = timeSwapBuff + (SystemClock.elapsedRealtime() - startTime);
            tvTimerDisplay.setText(formatTime(totalElapsedMillis));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_TOTAL_ELAPSED, totalElapsedMillis);
        outState.putBoolean(KEY_IS_RUNNING, isRunning);
        outState.putBoolean(KEY_IS_PAUSED, isPaused);
        outState.putLong(KEY_START_TIME, startTime);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public long getTotalElapsedMillis() {
        return totalElapsedMillis;
    }
}
