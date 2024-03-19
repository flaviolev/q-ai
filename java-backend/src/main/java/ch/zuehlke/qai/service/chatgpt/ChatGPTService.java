package ch.zuehlke.qai.service.chatgpt;

import ch.zuehlke.qai.model.chatgpt.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChatGPTService implements CreateCompletionMessage {

        private final WebClient webClient;

        @Value("${chatgpt.model}")
        private String model;

        @Value("${chatgpt.response-format}")
        private String responseFormat;

        @Value("${chatgpt.temperature}")
        private Double temperature;

        @Value("${chatgpt.max-tokens}")
        private Integer maxTokens;

        public ChatGPTService(WebClient.Builder webClientBuilder) {
                this.webClient = webClientBuilder.build();
        }

        public Mono<ChatGPTResponse> sendCompletionMessages(List<Message> messages) {

                ChatGPTRequest chatGPTRequest = new ChatGPTRequest(
                        model,
                        new ResponseFormat(responseFormat),
                        temperature,
                        maxTokens,
                        messages);

                return this.webClient.post()
                        .uri("/chat/completions")
                        .body(BodyInserters.fromValue(chatGPTRequest))
                        .retrieve()
                        .bodyToMono(ChatGPTResponse.class);
        }
}
