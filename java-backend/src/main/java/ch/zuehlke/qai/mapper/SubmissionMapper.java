package ch.zuehlke.qai.mapper;

import ch.zuehlke.qai.controller.response.ScoreDto;
import ch.zuehlke.qai.model.Quiz;
import ch.zuehlke.qai.model.Score;
import ch.zuehlke.qai.model.Submission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubmissionMapper {

    public ScoreDto mapScoreToDto(Score score) {
        List<ScoreDto.SubmissionDto> submissionDtoList = score.getSubmissions().stream().map(this::mapSubmissionToDto).toList();
        int numberOfQuestions = score.getSubmissions().stream()
                .findFirst()
                .map(Submission::getQuiz)
                .map(Quiz::getQuestions)
                .map(List::size)
                .orElseThrow(() -> new RuntimeException("Not able to determine number of questions for quiz."));
        return new ScoreDto(numberOfQuestions, submissionDtoList, score.getMessage());
    }

    public ScoreDto.SubmissionDto mapSubmissionToDto(Submission submission) {
        return new ScoreDto.SubmissionDto(submission.getQuiz().getId(), submission.getQuestion().getQuestionId(), submission.getAnswer() != null ? submission.getAnswer() .getId() : null, submission.getCorrectAnswer().getId());
    }
}
