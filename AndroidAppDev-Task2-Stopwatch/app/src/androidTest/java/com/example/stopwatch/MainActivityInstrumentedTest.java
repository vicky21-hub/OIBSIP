package com.example.stopwatch;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testInitialState() {
        onView(withId(R.id.tvTimerDisplay)).check(matches(withText("00:00:00")));
        onView(withId(R.id.btnStart)).check(matches(isDisplayed()));
        onView(withId(R.id.btnPause)).check(matches(isDisplayed()));
        onView(withId(R.id.btnReset)).check(matches(isDisplayed()));
        onView(withId(R.id.btnLap)).check(matches(isDisplayed()));
    }

    @Test
    public void testStartAndPauseStopwatch() {
        onView(withId(R.id.btnStart)).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.btnPause)).perform(click());
        onView(withId(R.id.btnReset)).perform(click());
        onView(withId(R.id.tvTimerDisplay)).check(matches(withText("00:00:00")));
    }
}
