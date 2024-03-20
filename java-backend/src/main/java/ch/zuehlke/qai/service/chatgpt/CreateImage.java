package ch.zuehlke.qai.service.chatgpt;

import ch.zuehlke.qai.model.chatgpt.ChatGPTImageResponse;
import ch.zuehlke.qai.model.chatgpt.ImageMessage;
import reactor.core.publisher.Mono;

public interface CreateImage {
    Mono<ChatGPTImageResponse> sendImageGenerationMessage(ImageMessage message);
}
