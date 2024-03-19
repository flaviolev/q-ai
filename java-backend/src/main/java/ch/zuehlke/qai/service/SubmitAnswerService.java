package ch.zuehlke.qai.service;

import ch.zuehlke.qai.controller.request.SubmitAnswerDto;
import ch.zuehlke.qai.model.Answer;
import ch.zuehlke.qai.model.Question;
import ch.zuehlke.qai.model.Score;
import ch.zuehlke.qai.model.Submission;
import ch.zuehlke.qai.repository.QuizRepository;
import ch.zuehlke.qai.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmitAnswerService implements SubmitAnswer {

    private final QuizRepository quizRepository;
    private final SubmissionRepository submissionRepository;

    @Override
    public Score submitAnswer(UUID sessionId, SubmitAnswerDto submitAnswerDto) {
        var quiz = quizRepository.findById(sessionId);
        if (quiz.isEmpty()) {
            throw new IllegalArgumentException("No quiz found for id " + sessionId);
        }

        Question question = quiz.get().getQuestions().stream()
                .filter(q -> q.getQuestionId().equals(submitAnswerDto.questionId()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No question found for id " + submitAnswerDto.questionId()));

        Answer answer = question.getAnswers().stream()
                .filter(a -> a.getId().equals(submitAnswerDto.answerId()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No answer found for id " + submitAnswerDto.answerId()));

        Answer correctAnswer = question.getAnswers().stream()
                .filter(Answer::isCorrect)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No correct answer found for question id " + question.getQuestionId()));

        Submission submission = new Submission();
        submission.setQuiz(quiz.get());
        submission.setQuestion(question);
        submission.setAnswer(answer);
        submission.setCorrectAnswer(correctAnswer);

        submissionRepository.save(submission);

        List<Submission> submissionsForQuiz = submissionRepository.findAllByQuizId(quiz.get().getId().toString());
        Score currentScore = new Score();
        currentScore.setSubmissions(submissionsForQuiz);
        return currentScore;
    }
}
