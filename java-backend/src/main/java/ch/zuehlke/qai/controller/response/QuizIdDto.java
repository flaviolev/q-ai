package ch.zuehlke.qai.controller.response;

import java.util.UUID;

public record QuizIdDto(
        UUID sessionId
) { }
