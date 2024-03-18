package ch.zuehlke.fullstack.hackathon.controller;

import ch.zuehlke.fullstack.hackathon.controller.response.QuizIdDto;
import ch.zuehlke.fullstack.hackathon.controller.response.TopicsDto;
import ch.zuehlke.fullstack.hackathon.service.GetAvailableTopics;
import ch.zuehlke.fullstack.hackathon.service.StartQuizSession;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final StartQuizSession startQuizSession;
    private final GetAvailableTopics getAvailableTopics;

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
}