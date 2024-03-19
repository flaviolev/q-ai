package ch.zuehlke.qai.repository;

import ch.zuehlke.qai.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
}
