package ch.zuehlke.qai.service;

import ch.zuehlke.qai.model.Question;

import java.util.Optional;
import java.util.UUID;

public interface GetNextQuestion {
    Optional<Question> getNextQuestion(UUID quizId);
}
