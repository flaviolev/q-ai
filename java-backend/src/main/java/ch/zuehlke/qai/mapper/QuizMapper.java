package ch.zuehlke.qai.mapper;

import ch.zuehlke.qai.controller.response.QuestionDTO;
import ch.zuehlke.qai.model.Answer;
import ch.zuehlke.qai.model.Question;
import ch.zuehlke.qai.model.Quiz;
import ch.zuehlke.qai.model.chatgpt.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public QuestionDTO mapQuestionToDto(Question question) {
        List<QuestionDTO.PossibleAnswerDTO> answers = question.getAnswers().stream().map(this::mapAnswerToDto).toList();
        return new QuestionDTO(question.getQuestionId(), answers, question.getText(), question.getImageUrl());
    }
    public QuestionDTO.PossibleAnswerDTO mapAnswerToDto(Answer answer) {
        return new QuestionDTO.PossibleAnswerDTO(answer.getId(), answer.getLabel(), answer.getText());
    }
}

