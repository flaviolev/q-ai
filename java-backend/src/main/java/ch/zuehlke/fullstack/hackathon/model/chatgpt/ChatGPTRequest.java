package ch.zuehlke.fullstack.hackathon.model.chatgpt;

import java.util.List;

public record ChatGPTRequest(
        String model,
        ResponseFormat response_format,
        double temperature,
        int max_tokens,
        List<Message> messages
) {
}
