package ch.zuehlke.qai.service;

import ch.zuehlke.qai.controller.request.SubmitAnswerDto;
import ch.zuehlke.qai.model.Submission;

import java.util.UUID;

public interface SubmitAnswer {
    Submission submitAnswer(UUID sessionId, SubmitAnswerDto submitAnswerDto);
}
