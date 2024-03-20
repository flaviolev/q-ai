package ch.zuehlke.qai.service.chatgpt;

import ch.zuehlke.qai.model.chatgpt.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChatGPTService implements CreateCompletionMessage, CreateImage {

        private final WebClient webClient;

        @Value("${chatgpt.model}")
        private String model;

        @Value("${chatgpt.image-model}")
        private String imageModel;

        @Value("${chatgpt.response-format}")
        private String responseFormat;

        @Value("${chatgpt.temperature}")
        private Double temperature;

        @Value("${chatgpt.max-tokens}")
        private Integer maxTokens;

        public ChatGPTService(WebClient.Builder webClientBuilder) {
                this.webClient = webClientBuilder.build();
        }

        @Override
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

        @Override
        public Mono<ChatGPTImageResponse> sendImageGenerationMessage(ImageMessage message) {
                ChatGPTImageRequest chatGPTImageRequest = new ChatGPTImageRequest(
                        imageModel,
                        message.prompt(),
                        message.numberOfImages(),
                        ImageQuality.Standard,
                        ImageFormat.URL,
                        ImageSize.Small);

                return this.webClient.post()
                        .uri("/images/generations")
                        .body(BodyInserters.fromValue(chatGPTImageRequest))
                        .retrieve()
                        .bodyToMono(ChatGPTImageResponse.class);
        }
}
