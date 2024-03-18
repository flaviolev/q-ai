package ch.zuehlke.fullstack.hackathon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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