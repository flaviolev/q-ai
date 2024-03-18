package ch.zuehlke.fullstack.hackathon.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizServiceTest {
    QuizService quizService;

    @BeforeEach
    void setUp() {
        quizService = new QuizService();
    }

    @Test
    void startQuizSession_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> quizService.startQuizSession("topic"));
    }
}