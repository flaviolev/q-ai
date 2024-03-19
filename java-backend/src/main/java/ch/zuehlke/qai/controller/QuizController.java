package ch.zuehlke.qai.controller;

import ch.zuehlke.qai.controller.response.QuizIdDto;
import ch.zuehlke.qai.controller.response.TopicsDto;
import ch.zuehlke.qai.service.GetAvailableTopics;
import ch.zuehlke.qai.service.StartQuizSession;
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
