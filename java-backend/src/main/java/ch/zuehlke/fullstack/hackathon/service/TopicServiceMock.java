package ch.zuehlke.fullstack.hackathon.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Profile("dev")
@Service
public class TopicServiceMock implements GetAvailableTopics {
    @Override
    public List<String> getAvailableTopics() {
        return List.of("Math", "Geography");
    }
}
