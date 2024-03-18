package ch.zuehlke.qai.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class QuizControllerTest {

    StartQuizSession startQuizSession;
    GetAvailableTopics getAvailableTopics;
    QuizController quizController;

    @BeforeEach
    void setUp() {
        startQuizSession = mock(StartQuizSession.class);
        getAvailableTopics = mock(GetAvailableTopics.class);
        quizController = new QuizController(startQuizSession, getAvailableTopics);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(startQuizSession, getAvailableTopics);
    }

    @Test
    void createQuiz_returnsSessionId_successfully() {
        UUID sessionId = UUID.randomUUID();
        when(startQuizSession.startQuizSession("topic")).thenReturn(sessionId);

        QuizIdDto quizIdDto = quizController.createQuiz("topic");

        assertThat(quizIdDto.sessionId()).isEqualTo(sessionId);

        verify(startQuizSession).startQuizSession("topic");
    }
}