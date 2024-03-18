package ch.zuehlke.fullstack.hackathon.service.chatgpt;

import ch.zuehlke.fullstack.hackathon.model.chatgpt.ChatGPTResponse;
import reactor.core.publisher.Mono;

public interface CreateQuiz {
    Mono<ChatGPTResponse> createQuestions(String topic, int amount);
}
