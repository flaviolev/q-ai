package ch.zuehlke.qai.model.chatgpt;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageSize {
    Small("256x256"),
    Medium("512x512"),
    Large("1024x1024");

    private final String size;

    ImageSize(String size) {
        this.size = size;
    }

    @JsonValue
    public String getSize() {
        return size;
    }
}
