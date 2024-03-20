package ch.zuehlke.qai.model.chatgpt;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageQuality {
    Standard("standard"),
    HD("hd");

    private final String quality;

    ImageQuality(String quality) {
        this.quality = quality;
    }

    @JsonValue
    public String getQuality() {
        return quality;
    }
}
