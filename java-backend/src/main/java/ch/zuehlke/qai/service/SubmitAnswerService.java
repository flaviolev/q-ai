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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmitAnswerService implements SubmitAnswer, GetCurrentScoring {

    private final QuizRepository quizRepository;
    private final SubmissionRepository submissionRepository;

    @Override
    public Submission submitAnswer(UUID quizId, SubmitAnswerDto submitAnswerDto) {

        List<Submission> existingSubmissionsForQuiz = submissionRepository.findAllByQuizId(quizId);

        Optional<Submission> conflictSubmission = existingSubmissionsForQuiz.stream()
                .filter(submission -> submission.getQuestion().getQuestionId().equals(submitAnswerDto.questionId()))
                .findAny();

        if (conflictSubmission.isPresent()) {
            throw new IllegalStateException("User already answered this question.");
        }

        var quiz = quizRepository.findById(quizId);
        if (quiz.isEmpty()) {
            throw new IllegalArgumentException("No quiz found for id " + quizId);
        }

        Question question = quiz.get().getQuestions().stream()
                .filter(q -> q.getQuestionId().equals(submitAnswerDto.questionId()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No question found for id " + submitAnswerDto.questionId()));

        Optional<Answer> answer = question.getAnswers().stream()
                .filter(a -> a.getId().equals(submitAnswerDto.answerId()))
                .findAny();

        Answer correctAnswer = question.getAnswers().stream()
                .filter(Answer::isCorrect)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No correct answer found for question id " + question.getQuestionId()));

        Submission submission = new Submission();
        submission.setQuiz(quiz.get());
        submission.setQuestion(question);
        submission.setAnswer(answer.orElse(null));
        submission.setCorrectAnswer(correctAnswer);

        return submissionRepository.save(submission);
    }

    @Override
    public Score getCurrentScoring(UUID quizId) {
        List<Submission> submissionsForQuiz = submissionRepository.findAllByQuizId(quizId);
        Score currentScore = new Score();
        currentScore.setSubmissions(submissionsForQuiz);
        currentScore.setMessage("Congratulations, well done!");
        return currentScore;
    }
}