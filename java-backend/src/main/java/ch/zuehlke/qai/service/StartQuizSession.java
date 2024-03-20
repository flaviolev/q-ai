package ch.zuehlke.qai.service;

import java.util.Optional;
import java.util.UUID;

public interface StartQuizSession {
    UUID startQuizSession(String topic, Optional<Integer> numberOfQuestions, Optional<String> difficultyLevel);
}
