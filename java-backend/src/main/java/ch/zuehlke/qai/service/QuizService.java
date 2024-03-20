package ch.zuehlke.qai.service;

import ch.zuehlke.qai.mapper.QuizMapper;
import ch.zuehlke.qai.model.Question;
import ch.zuehlke.qai.model.Quiz;
import ch.zuehlke.qai.model.Submission;
import ch.zuehlke.qai.model.chatgpt.ChatGPTResponse;
import ch.zuehlke.qai.model.chatgpt.Choice;
import ch.zuehlke.qai.model.chatgpt.Message;
import ch.zuehlke.qai.repository.QuizRepository;
import ch.zuehlke.qai.repository.SubmissionRepository;
import ch.zuehlke.qai.service.chatgpt.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Profile("!dev")
@Service
public class QuizService implements StartQuizSession, GetNextQuestion {

    private final QuizRepository quizRepository;
    private final SubmissionRepository submissionRepository;
    private final ChatGPTService chatGPTService;
    private final QuizMapper quizMapper;

    @Value("${quiz.number-of-questions-per-round}")
    Integer numberOfQuestionsForQuizDefault;
    String difficultyLevelDefault = "difficult";

    public UUID startQuizSession(String topic, Optional<Integer> numberOfQuestions, Optional<String> difficultyLevel) {
        int n = numberOfQuestions.orElse(numberOfQuestionsForQuizDefault);
        String difficulty = difficultyLevel.orElse(difficultyLevelDefault);
        Message message = new Message("user",
                String.format("I would like to have %d %s questions about %s.", n, difficulty, topic)
        );

        Message systemMessage = new Message("system", "When a user requests questions, your task is to " +
                "generate the user specified number of questions up to a maximum of 10. Each question will focus on a " +
                "given topic and include four possible answers. " +
                "Consider different difficulties levels for the questions (easy, medium, hard)." +
                "These answers should be labeled A, B, C, and D." +
                "Your responses must be formatted in JSON, adhering to the following structure. The response should" +
                "return a list of questions with each question must include a key named 'text' that contains the text of the question being asked." +
                "Alongside the text, provide an array named answers. This array should contain four items, " +
                "each representing a possible answer. Each item in the array must include 'label' key, " +
                "indicating the letter (A, B, C, or D) associated with this answer, an 'text' key holding " +
                "the text for the answer and a 'correct' key, with a value of either 0 (indicating the answer " +
                "is incorrect) or 1 (indicating the answer is correct).");

        Quiz quiz = new Quiz();
        quiz.setTopic(topic);
        Quiz savedQuiz = quizRepository.save(quiz);
        ChatGPTResponse response = chatGPTService
                .sendCompletionMessages(List.of(systemMessage, message))
                .block();

        if (response == null) {
            throw new RuntimeException("ChatGPT service did not respond");
        }

        response.choices().stream()
                .map(Choice::message)
                .map(quizMapper::mapToQuiz)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach((generatedQuiz) -> {
                    List<Question> questions = generatedQuiz.getQuestions();
                    AtomicInteger questionCount = new AtomicInteger(1);
                    questions.forEach(question -> {
                        question.setQuiz(savedQuiz);
                        question.setPosition(questionCount.getAndIncrement());
                    });
                    savedQuiz.setQuestions(questions);
                    quizRepository.save(savedQuiz);
                });

        return savedQuiz.getId();
    }

    @Override
    public Optional<Question> getNextQuestion(UUID quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        List<Submission> submissions = submissionRepository.findAllByQuizId(quizId);
        return quiz.getQuestions().stream()
                .filter(question -> !containsQuestion(submissions, question.getQuestionId()))
                .min(Comparator.comparingInt(Question::getPosition));
    }

    private boolean containsQuestion(List<Submission> submissions, UUID questionId) {
        return submissions.stream()
                .anyMatch(submission -> submission.getQuestion().getQuestionId().equals(questionId));
    }
}
