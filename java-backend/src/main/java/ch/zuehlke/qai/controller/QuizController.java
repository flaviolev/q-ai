package ch.zuehlke.qai.controller;

import ch.zuehlke.qai.controller.response.QuizIdDto;
import ch.zuehlke.qai.service.StartQuizSession;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final StartQuizSession startQuizSession;

    @PostMapping("/api/quiz")
    @Operation(summary = "Create a new quiz",
            description = "This can be used to create a new quiz")
    @ApiResponse(responseCode = "200", description = "Successfully returned new quiz id")
    @ApiResponse(responseCode = "500", description = "Something failed internally")
    public QuizIdDto createQuiz(@RequestParam("topic") String topic) {
        UUID sessionId = startQuizSession.startQuizSession(topic);
        return new QuizIdDto(sessionId);
    }
}
