package ch.zuehlke.qai.repository;

import ch.zuehlke.qai.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizRepository  extends JpaRepository<Quiz, UUID> {
}