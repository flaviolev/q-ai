export interface QuizIdDto {
    id: string;
}

export interface TopicsDto {
    topicList: string[];
}

export interface QuestionDto {
    id: string;
    text: string;
    possibleAnswers: PossibleAnswerDto[];
    imageUrl: string;
}

export interface PossibleAnswerDto {
    id: string;
    text: string;
    label: string;
}

export interface ScoreDto {
    numberOfQuestions: number;
    correctCount: number;
    submissionDtoList: SubmissionDto[];
    message: string;
}

export interface SubmissionDto {
    quizId: string;
    questionId: string;
    answerId: string;
    correctAnswerId: string;
}

export interface SubmitAnswerDto {
    userId: string;
    questionId: string;
    answerId: string;
}