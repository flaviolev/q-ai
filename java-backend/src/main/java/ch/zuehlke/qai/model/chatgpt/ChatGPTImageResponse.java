package ch.zuehlke.qai.model.chatgpt;

import java.util.List;
import java.util.Optional;

public record ChatGPTImageResponse(
        Long created,
        List<Image> data
) {
    public Optional<String> getImageValueAtIndex(int idx) {
        return data.get(idx).getValue();
    }
}
