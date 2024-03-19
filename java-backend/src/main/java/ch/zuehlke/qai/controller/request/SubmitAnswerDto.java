package ch.zuehlke.qai.controller.request;

import java.util.UUID;

public record SubmitAnswerDto(
        UUID questionId,
        UUID answerId,
        UUID userId
) {
}
