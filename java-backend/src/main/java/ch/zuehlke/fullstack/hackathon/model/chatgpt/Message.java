package ch.zuehlke.fullstack.hackathon.model.chatgpt;

public record Message(
        String role,
        String content
) {
}
