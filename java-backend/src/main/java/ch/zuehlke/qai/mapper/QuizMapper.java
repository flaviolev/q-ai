package ch.zuehlke.qai.mapper;

import ch.zuehlke.qai.model.Quiz;
import ch.zuehlke.qai.model.chatgpt.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public QuizMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<Quiz> mapToQuiz(Message message) {
        try {
            String formattedMessage = message.content().trim();
            Quiz quiz = objectMapper.readValue(formattedMessage, Quiz.class);
            return Optional.of(quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
