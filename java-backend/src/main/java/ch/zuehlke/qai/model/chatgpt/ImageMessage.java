package ch.zuehlke.qai.model.chatgpt;

public record ImageMessage(
        String prompt,
        Integer numberOfImages
) {
}
