package ch.zuehlke.qai.model.chatgpt;


import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageFormat {
    URL("url"),
    BASE64("b64_json");

    private final String format;

    ImageFormat(String format) {
        this.format = format;
    }

    @JsonValue
    public String getFormat() {
        return format;
    }
}
