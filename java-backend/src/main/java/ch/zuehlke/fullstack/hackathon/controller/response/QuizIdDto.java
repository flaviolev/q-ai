package ch.zuehlke.fullstack.hackathon.controller.response;

import java.util.UUID;

public record QuizIdDto(
        UUID sessionId
) { }
