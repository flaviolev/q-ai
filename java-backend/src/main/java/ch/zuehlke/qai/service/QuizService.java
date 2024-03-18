package ch.zuehlke.qai.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Profile("!dev")
@Service
public class QuizService implements StartQuizSession {
    public UUID startQuizSession(String topic) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
