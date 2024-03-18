package ch.zuehlke.qai.model.chatgpt;

public record Choice(
        int index,
        Message message,
        Object logprobs,
        String finish_reason
) { }
