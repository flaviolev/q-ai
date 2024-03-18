package ch.zuehlke.qai.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("!dev")
@Service
public class TopicService implements GetAvailableTopics {
    @Override
    public List<String> getAvailableTopics() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
