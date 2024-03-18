package ch.zuehlke.fullstack.hackathon.model.chatgpt;

public record Choice(
        int index,
        Message message,
        Object logprobs,
        String finish_reason
) { }
