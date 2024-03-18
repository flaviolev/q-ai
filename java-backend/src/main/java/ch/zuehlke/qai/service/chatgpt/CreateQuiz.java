package ch.zuehlke.qai.service.chatgpt;

import ch.zuehlke.qai.model.chatgpt.ChatGPTResponse;
import reactor.core.publisher.Mono;

public interface CreateQuiz {
    Mono<ChatGPTResponse> createQuestions(String topic, int amount);
}
