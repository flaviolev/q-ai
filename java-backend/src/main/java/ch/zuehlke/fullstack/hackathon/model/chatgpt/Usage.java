package ch.zuehlke.fullstack.hackathon.model.chatgpt;

public record Usage(
        int prompt_tokens,
        int completion_tokens,
        int total_tokens
) { }
