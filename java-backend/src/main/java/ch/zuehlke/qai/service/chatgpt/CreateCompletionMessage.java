package ch.zuehlke.qai.service.chatgpt;

import ch.zuehlke.qai.model.chatgpt.ChatGPTResponse;
import ch.zuehlke.qai.model.chatgpt.Message;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CreateCompletionMessage {
    Mono<ChatGPTResponse> sendCompletionMessages(List<Message> messages);
}
