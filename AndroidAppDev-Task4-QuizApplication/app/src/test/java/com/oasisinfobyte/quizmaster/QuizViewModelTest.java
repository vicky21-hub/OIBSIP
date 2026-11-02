package com.oasisinfobyte.quizmaster;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuizViewModelTest {
    private QuizViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new QuizViewModel();
    }

    @Test
    public void testInitialState() {
        assertEquals(0, viewModel.getCurrentIndex());
        assertEquals(15, viewModel.getTotalQuestions());
        assertEquals(0, viewModel.getScore());
        assertFalse(viewModel.isAnswered());
        assertNotNull(viewModel.getCurrentQuestion());
    }

    @Test
    public void testCheckAnswerAndNext() {
        QuizQuestion q = viewModel.getCurrentQuestion();
        int correctIndex = q.getCorrectAnswerIndex();

        boolean result = viewModel.checkAnswer(correctIndex);
        assertTrue(result);
        assertTrue(viewModel.isAnswered());
        assertEquals(1, viewModel.getScore());

        boolean hasMore = viewModel.nextQuestion();
        assertTrue(hasMore);
        assertEquals(1, viewModel.getCurrentIndex());
        assertFalse(viewModel.isAnswered());
    }
}
