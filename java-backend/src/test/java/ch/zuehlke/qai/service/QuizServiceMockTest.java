package ch.zuehlke.qai.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuizServiceMockTest {

    QuizServiceMock quizServiceMock;

    @BeforeEach
    void setUp() {
        quizServiceMock = new QuizServiceMock();
    }

    @Test
    void startQuizSession_returnsRandomUUID_successfully() {
        assertNotNull(quizServiceMock.startQuizSession("topic"));
    }
}