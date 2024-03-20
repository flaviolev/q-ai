package ch.zuehlke.qai.controller.request;

import jakarta.annotation.Nullable;

import java.util.UUID;

public record SubmitAnswerDto(
        UUID questionId,
        @Nullable
        UUID answerId,
        UUID userId
) {
}
