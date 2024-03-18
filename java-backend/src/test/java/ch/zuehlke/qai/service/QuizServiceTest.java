package ch.zuehlke.qai.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class QuizServiceTest {
    QuizService quizService;

    @BeforeEach
    void setUp() {
        quizService = new QuizService(null);
    }

    @Test
    void startQuizSession_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> quizService.startQuizSession("topic"));
    }
}