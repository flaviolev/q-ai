package ch.zuehlke.qai.service.chatgpt;

import ch.zuehlke.qai.model.chatgpt.ChatGPTRequest;
import ch.zuehlke.qai.model.chatgpt.ChatGPTResponse;
import ch.zuehlke.qai.model.chatgpt.Message;
import ch.zuehlke.qai.model.chatgpt.ResponseFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChatGPTService implements CreateQuiz {

        private final WebClient webClient;

        @Value("${open-api.model}")
        private String model;

        @Value("${open-api.response-format}")
        private String responseFormat;

        @Value("${open-api.temperature}")
        private Double temperature;

        @Value("${open-api.max-tokens}")
        private Integer maxTokens;

        public ChatGPTService(WebClient.Builder webClientBuilder) {
                this.webClient = webClientBuilder.build();
        }

        @Override
        public Mono<ChatGPTResponse> createQuestions(String topic, int amount) {

                Message systemMessage = new Message("system", "When a user requests questions, your task is to " +
                        "generate a specified number of questions up to a maximum of 10. Each question will focus on a " +
                        "given topic and include four possible answers. These answers should be labeled A, B, C, and D." +
                        " Your responses must be formatted in JSON, adhering to the following structure. Each response " +
                        "must include a key named 'question' that contains the text of the question being asked." +
                        " Alongside the question, provide an array named answers. This array should contain four items, " +
                        "each representing a possible answer. Each item in the array must include 'label' key, " +
                        "indicating the letter (A, B, C, or D) associated with this answer, an 'answer' key holding " +
                        "the text for the answer and a 'correct' key, with a value of either 0 (indicating the answer " +
                        "is incorrect) or 1 (indicating the answer is correct).");

                Message userMessage = new Message("user",
                        String.format("I would like to have %d questions about %s", amount, topic)
                );

                ChatGPTRequest chatGPTRequest = new ChatGPTRequest(
                        model,
                        new ResponseFormat(responseFormat),
                        temperature,
                        maxTokens,
                        List.of(systemMessage, userMessage));

                return this.webClient.post()
                        .uri("/chat/completions")
                        .body(BodyInserters.fromValue(chatGPTRequest))
                        .retrieve()
                        .bodyToMono(ChatGPTResponse.class);
        }
}
