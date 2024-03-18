package ch.zuehlke.qai.model.chatgpt;

public record Message(
        String role,
        String content
) {
}
