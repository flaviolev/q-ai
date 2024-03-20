package ch.zuehlke.qai.model.chatgpt;

import java.util.Optional;

public record Image(
        String url,
        String b64_json,
        String revised_prompt
) {
    Optional<String> getValue() {
        if (url != null) {
            return Optional.of(url);
        } else if (b64_json != null) {
            return Optional.of(b64_json);
        }
        return Optional.empty();
    }
}
