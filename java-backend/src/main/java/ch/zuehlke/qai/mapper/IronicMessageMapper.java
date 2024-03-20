package ch.zuehlke.qai.mapper;

import ch.zuehlke.qai.model.IronicMessage;
import ch.zuehlke.qai.model.chatgpt.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IronicMessageMapper {

    private final ObjectMapper objectMapper;

    public Optional<IronicMessage> mapToIronicMessage(Message message) {
        try {
            String formattedMessage = message.content().trim();
            IronicMessage ironicMessage = objectMapper.readValue(formattedMessage, IronicMessage.class);
            return Optional.of(ironicMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
