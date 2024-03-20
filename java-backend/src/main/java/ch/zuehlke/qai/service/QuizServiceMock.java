package ch.zuehlke.qai.service;

import ch.zuehlke.qai.model.Answer;
import ch.zuehlke.qai.model.Question;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("dev")
@Service
public class QuizServiceMock implements StartQuizSession, GetNextQuestion {
    @Override
    public UUID startQuizSession(String topic, Optional<Integer> n, Optional<String> d) {
        return UUID.randomUUID();
    }

    @Override
    public Optional<Question> getNextQuestion(UUID quizId) {
        Question question = new Question();
        question.setQuestionId(UUID.randomUUID());
        question.setText("How many people work here?");
        Answer answer1 = new Answer();
        answer1.setText("1");
        answer1.setLabel("A");
        answer1.setCorrect(false);
        Answer answer2 = new Answer();
        answer2.setText("700");
        answer2.setLabel("B");
        answer2.setCorrect(true);
        question.setAnswers(List.of(answer1, answer2));
        return Optional.of(question);
    }
}
