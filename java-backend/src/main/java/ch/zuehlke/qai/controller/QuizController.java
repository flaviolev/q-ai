package ch.zuehlke.qai.controller;

import ch.zuehlke.qai.controller.request.SubmitAnswerDto;
import ch.zuehlke.qai.controller.response.QuizIdDto;
import ch.zuehlke.qai.controller.response.TopicsDto;
import ch.zuehlke.qai.service.GetAvailableTopics;
import ch.zuehlke.qai.service.StartQuizSession;
import ch.zuehlke.qai.service.SubmitAnswer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final StartQuizSession startQuizSession;
    private final GetAvailableTopics getAvailableTopics;
    private final SubmitAnswer submitAnswer;

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

    @Operation(summary = "Submit a quiz answer",
            description = "This can be used to submit a quiz answer")
    @ApiResponse(responseCode = "200", description = "Successfully submitted answer")
    @ApiResponse(responseCode = "500", description = "Something failed internally")
    @PostMapping("/submit")
    public ResponseEntity<Void> getTopics(@RequestParam("sessionId") UUID sessionId, @RequestBody SubmitAnswerDto submitAnswerDto) {
        submitAnswer.submitAnswer(sessionId, submitAnswerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
