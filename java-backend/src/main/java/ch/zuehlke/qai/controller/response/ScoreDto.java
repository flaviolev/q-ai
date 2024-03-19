package ch.zuehlke.qai.controller.response;

import java.util.List;
import java.util.UUID;

public record ScoreDto(int numberOfQuestions, List<SubmissionDto> submissionDtoList) {
    public record SubmissionDto(UUID quizId, UUID questionId, UUID answerId, UUID correctAnswerId) {
    }
}
