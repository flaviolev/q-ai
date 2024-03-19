package ch.zuehlke.qai.service;

import ch.zuehlke.qai.model.Topic;
import ch.zuehlke.qai.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Profile("dev")
@Service
public class TopicServiceMock implements GetAvailableTopics {

    private final TopicRepository topicRepository;

    @Override
    public List<String> getAvailableTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream().map(Topic::getName).toList();
    }
}
