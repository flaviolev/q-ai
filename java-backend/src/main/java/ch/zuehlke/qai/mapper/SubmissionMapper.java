package ch.zuehlke.qai.mapper;

import ch.zuehlke.qai.controller.response.ScoreDto;
import ch.zuehlke.qai.model.Score;
import ch.zuehlke.qai.model.Submission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubmissionMapper {

    public ScoreDto mapScoreToDto(Score score) {
        List<ScoreDto.SubmissionDto> submissionDtoList = score.getSubmissions().stream().map(this::mapSubmissionToDto).toList();
        return new ScoreDto(submissionDtoList);
    }

    public ScoreDto.SubmissionDto mapSubmissionToDto(Submission submission) {
        return new ScoreDto.SubmissionDto(submission.getQuizId(), submission.getQuestionId(), submission.getAnswerId(), submission.getCorrectAnswerId());
    }
}
