package ch.zuehlke.qai.controller;

import ch.zuehlke.qai.controller.response.QuizIdDto;
import ch.zuehlke.qai.mapper.QuizMapper;
import ch.zuehlke.qai.service.GetAvailableTopics;
import ch.zuehlke.qai.service.GetNextQuestion;
import ch.zuehlke.qai.service.StartQuizSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class QuizControllerTest {

    StartQuizSession startQuizSession;
    GetAvailableTopics getAvailableTopics;

    GetNextQuestion getNextQuestion;
    QuizController quizController;

    QuizMapper quizMapper;

    @BeforeEach
    void setUp() {
        startQuizSession = mock(StartQuizSession.class);
        getAvailableTopics = mock(GetAvailableTopics.class);
        getNextQuestion = mock(GetNextQuestion.class);
        quizMapper = mock(QuizMapper.class);
        quizController = new QuizController(startQuizSession, getAvailableTopics, getNextQuestion, quizMapper);
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