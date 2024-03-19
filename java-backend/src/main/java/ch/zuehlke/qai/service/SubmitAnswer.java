package ch.zuehlke.qai.service;

import ch.zuehlke.qai.controller.request.SubmitAnswerDto;
import ch.zuehlke.qai.model.Score;

import java.util.UUID;

public interface SubmitAnswer {
    Score submitAnswer(UUID sessionId, SubmitAnswerDto submitAnswerDto);
}
