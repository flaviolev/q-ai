package ch.zuehlke.qai.service;

import ch.zuehlke.qai.model.Score;

import java.util.UUID;

public interface GetCurrentScoring {
    Score getCurrentScoring(UUID quizId);
}
