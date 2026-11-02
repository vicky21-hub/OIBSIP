package com.oasisinfobyte.quizmaster;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class QuizRepositoryTest {

    @Test
    public void testGetQuestionsNotEmpty() {
        List<QuizQuestion> questions = QuizRepository.getQuestions();
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(15, questions.size());
    }

    @Test
    public void testQuestionValidity() {
        List<QuizQuestion> questions = QuizRepository.getQuestions();
        for (QuizQuestion q : questions) {
            assertNotNull(q.getQuestion());
            assertNotNull(q.getOptions());
            assertEquals(4, q.getOptions().length);
            assertTrue(q.getCorrectAnswerIndex() >= 0 && q.getCorrectAnswerIndex() < 4);
            assertNotNull(q.getExplanation());
        }
    }
}
