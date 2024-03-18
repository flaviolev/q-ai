package ch.zuehlke.qai.repository;

import ch.zuehlke.qai.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository  extends JpaRepository<Quiz, Long> {
}