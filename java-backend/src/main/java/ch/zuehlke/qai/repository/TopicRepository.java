package ch.zuehlke.qai.repository;

import ch.zuehlke.qai.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}