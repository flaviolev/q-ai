package ch.zuehlke.fullstack.hackathon.controller;

import ch.zuehlke.fullstack.hackathon.controller.response.SessionIdDTO;
import ch.zuehlke.fullstack.hackathon.service.StartQuizSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class QuizControllerTest {

    StartQuizSession startQuizSession;
    QuizController quizController;

    @BeforeEach
    void setUp() {
        startQuizSession = mock(StartQuizSession.class);
        quizController = new QuizController(startQuizSession);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(startQuizSession);
    }

    @Test
    void createQuiz_returnsSessionId_successfully() {
        UUID sessionId = UUID.randomUUID();
        when(startQuizSession.startQuizSession("topic")).thenReturn(sessionId);

        SessionIdDTO sessionIdDTO = quizController.createQuiz("topic");

        assertThat(sessionIdDTO.sessionId()).isEqualTo(sessionId);

        verify(startQuizSession).startQuizSession("topic");
    }
}