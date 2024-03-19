package ch.zuehlke.qai.controller.response;

import java.util.List;
import java.util.UUID;

public record QuestionDTO(UUID id, List<PossibleAnswerDTO> possibleAnswers, String text) {
    public record PossibleAnswerDTO(UUID id, String label, String text) {
    }
}
