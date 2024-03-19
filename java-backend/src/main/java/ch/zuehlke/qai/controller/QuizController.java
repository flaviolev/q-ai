package ch.zuehlke.qai.controller;

import ch.zuehlke.qai.controller.request.SubmitAnswerDto;
import ch.zuehlke.qai.controller.response.QuestionDTO;
import ch.zuehlke.qai.controller.response.QuizIdDto;
import ch.zuehlke.qai.controller.response.ScoreDto;
import ch.zuehlke.qai.controller.response.TopicsDto;
import ch.zuehlke.qai.mapper.QuizMapper;
import ch.zuehlke.qai.mapper.SubmissionMapper;
import ch.zuehlke.qai.model.Question;
import ch.zuehlke.qai.model.Score;
import ch.zuehlke.qai.model.Submission;
import ch.zuehlke.qai.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final StartQuizSession startQuizSession;
    private final GetAvailableTopics getAvailableTopics;
    private final SubmitAnswer submitAnswer;
    private final GetNextQuestion getNextQuestion;
    private final GetCurrentScoring getCurrentScoring;
    private final QuizMapper quizMapper;
    private final SubmissionMapper submissionMapper;

    @PostMapping
    @Operation(summary = "Create a new quiz",
            description = "This can be used to create a new quiz")
    @ApiResponse(responseCode = "200", description = "Successfully returned new quiz id")
    @ApiResponse(responseCode = "500", description = "Something failed internally")
    public QuizIdDto createQuiz(@RequestParam("topic") String topic) {
        UUID sessionId = startQuizSession.startQuizSession(topic);
        return new QuizIdDto(sessionId);
    }

    @Operation(summary = "Get list of available topics for quizzes.")
    @ApiResponse(responseCode = "200", description = "Successfully returned list")
    @ApiResponse(responseCode = "500", description = "Something failed internally")
    @GetMapping("/topics")
    public ResponseEntity<TopicsDto> getTopics() {
        List<String> topicList = getAvailableTopics.getAvailableTopics();
        TopicsDto response = new TopicsDto(topicList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get next question with possible answers of the provided quiz.")
    @ApiResponse(responseCode = "200", description = "Successfully returned list")
    @ApiResponse(responseCode = "500", description = "Something failed internally")
    @GetMapping("{id}/next-question")
    public ResponseEntity<QuestionDTO> getNextQuestion(@PathVariable UUID id) {
        Optional<Question> nextQuestion = getNextQuestion.getNextQuestion(id);
        QuestionDTO response = nextQuestion.map(quizMapper::mapQuestionToDto).orElseThrow();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Submit a quiz answer",
            description = "This can be used to submit a quiz answer")
    @ApiResponse(responseCode = "200", description = "Successfully submitted answer")
    @ApiResponse(responseCode = "500", description = "Something failed internally")
    @PostMapping("/submit")
    public ResponseEntity<ScoreDto.SubmissionDto> submitAnswer(@RequestParam("quizId") UUID quizId, @RequestBody SubmitAnswerDto submitAnswerDto) {
        Submission submission = submitAnswer.submitAnswer(quizId, submitAnswerDto);
        ScoreDto.SubmissionDto submissionDto = submissionMapper.mapSubmissionToDto(submission);
        return ResponseEntity.ok(submissionDto);
    }

    @Operation(summary = "Submit a quiz answer",
            description = "This can be used to submit a quiz answer")
    @ApiResponse(responseCode = "200", description = "Successfully submitted answer")
    @ApiResponse(responseCode = "500", description = "Something failed internally")
    @GetMapping("/score")
    public ResponseEntity<ScoreDto> getCurrentScoring(@RequestParam("quizId") UUID quizId) {
        Score score = getCurrentScoring.getCurrentScoring(quizId);
        ScoreDto scoreDto = submissionMapper.mapScoreToDto(score);
        return ResponseEntity.ok(scoreDto);
    }
}
