package ch.zuehlke.fullstack.hackathon.model.chatgpt;

import java.util.List;

public record ChatGPTResponse(
        String id,
        String object,
        Long created,
        String model,
        List<Choice> choices,
        Usage usage,
        String system_fingerprint
) {
}
