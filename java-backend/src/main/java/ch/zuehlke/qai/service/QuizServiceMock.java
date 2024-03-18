package ch.zuehlke.qai.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Profile("dev")
@Service
public class QuizServiceMock implements StartQuizSession {
    @Override
    public UUID startQuizSession(String topic) {
        return UUID.randomUUID();
    }
}
