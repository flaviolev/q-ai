package ch.zuehlke.qai.service;

import ch.zuehlke.qai.model.Quiz;
import ch.zuehlke.qai.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Profile("!dev")
@Service
public class QuizService implements StartQuizSession {

    private final QuizRepository quizRepository;

    public UUID startQuizSession(String topic) {
        // api mit topic anfrage
        Quiz quiz = new Quiz();
        quiz.setTopic(topic);
        Quiz savedQuiz = quizRepository.save(quiz);
        return savedQuiz.getId();
    }
}
