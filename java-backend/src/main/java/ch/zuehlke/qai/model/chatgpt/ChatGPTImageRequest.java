package ch.zuehlke.qai.model.chatgpt;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChatGPTImageRequest(
        String model,
        String prompt,
        @JsonProperty("n") Integer numberOfImages,
        ImageQuality quality,
        ImageFormat response_format,
        ImageSize size

) {
    public ChatGPTImageRequest {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model must not be null or empty");
        }
        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException("Prompt must not be null or empty");
        }
        if (numberOfImages == null || numberOfImages < 1) {
            throw new IllegalArgumentException("Number of images must be greater than 0");
        }
    }
}
