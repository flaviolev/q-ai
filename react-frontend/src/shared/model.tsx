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
}

export interface PossibleAnswerDto {
    id: string;
    text: string;
    label: string;
}