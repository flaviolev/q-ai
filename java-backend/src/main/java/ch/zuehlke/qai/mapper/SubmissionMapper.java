package ch.zuehlke.qai.mapper;

import ch.zuehlke.qai.controller.response.ScoreDto;
import ch.zuehlke.qai.model.Quiz;
import ch.zuehlke.qai.model.Score;
import ch.zuehlke.qai.model.Submission;
import ch.zuehlke.qai.model.chatgpt.ChatGPTResponse;
import ch.zuehlke.qai.model.chatgpt.Choice;
import ch.zuehlke.qai.model.chatgpt.Message;
import ch.zuehlke.qai.service.chatgpt.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubmissionMapper {

    private final ChatGPTService chatGPTService;

    public ScoreDto mapScoreToDto(Score score) {
        List<ScoreDto.SubmissionDto> submissionDtoList = score.getSubmissions().stream().map(this::mapSubmissionToDto).toList();
        int numberOfQuestions = score.getSubmissions().stream()
                .findFirst()
                .map(Submission::getQuiz)
                .map(Quiz::getQuestions)
                .map(List::size)
                .orElseThrow(() -> new RuntimeException("Not able to determine number of questions for quiz."));

        long numberOfCorrectAnswers = score.getSubmissions().stream()
                .filter(submission -> submission.getCorrectAnswer().equals(submission.getAnswer()))
                .count();


        Message systemMessage = new Message(
                "system",
                "Your role is to give sarcastic comments to the users score. Response in a json format." +
                        "The response should include a key named 'message' that contains your ironic comment. "
        );
        Message message = new Message(
                "user",
                String.format("My score is %d of %d points.", numberOfCorrectAnswers, numberOfQuestions)
        );

        ChatGPTResponse chatGPTResponse = chatGPTService.sendCompletionMessages(List.of(systemMessage, message)).block();

        if (chatGPTResponse == null ||
                chatGPTResponse.choices() == null ||
                chatGPTResponse.choices().isEmpty()) {
            throw new RuntimeException("ChatGPT response is empty.");
        }

        String ironicScoreMessage = chatGPTResponse.choices()
                .stream()
                .findFirst()
                .map(Choice::message)
                .map(Message::content)
                .orElse("Wow, you are so smart!");

        return new ScoreDto(numberOfQuestions, submissionDtoList, ironicScoreMessage);
    }

    public ScoreDto.SubmissionDto mapSubmissionToDto(Submission submission) {
        return new ScoreDto.SubmissionDto(submission.getQuiz().getId(), submission.getQuestion().getQuestionId(), submission.getAnswer() != null ? submission.getAnswer().getId() : null, submission.getCorrectAnswer().getId());
    }
}
