package ch.zuehlke.qai.service;

import ch.zuehlke.qai.controller.request.SubmitAnswerDto;

import java.util.UUID;

public interface SubmitAnswer {
    void submitAnswer(UUID sessionId, SubmitAnswerDto submitAnswerDto);
}
