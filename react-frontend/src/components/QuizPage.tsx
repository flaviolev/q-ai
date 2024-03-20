import styled from 'styled-components';
import {useEffect, useState} from 'react';
import Title from "../shared/Title.tsx";
import {useNavigate, useParams} from "react-router-dom"
import {QuestionDto, SubmissionDto} from "../shared/model.tsx";
import SubTitle from "../shared/SubTitle.tsx";
import Grid from "../shared/Grid.tsx";
import PossibleAnswer from "./quiz/PossibleAnswer.tsx";
import remoteService from "../services/RemoteService.tsx";
import Button from "../shared/Button.tsx";
import LoadingPageWithText from "../shared/LoadingPageWithText.tsx";

const Section = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;


export default function QuizPage() {

    const navigate = useNavigate();

    const [question, setQuestion] = useState<QuestionDto | undefined>(undefined);
    const [isLoading, setIsLoading] = useState<Boolean | undefined>(false);
    const [timeoutId, setTimeoutId] = useState<NodeJS.Timeout | undefined>(undefined);

    const {id} = useParams();

    const [selectedId, setSelectedId] = useState<string | undefined>();
    const [answerId, setAnswerId] = useState<string | undefined>();

    useEffect(() => {
        getQuestion();
    }, []);

    function navigateToScorePage() {
        navigate(`/score/${id}`);
    }

    function getQuestion() {
        setSelectedId(undefined);
        setAnswerId(undefined);
        remoteService.get<QuestionDto>(`/quiz/${id}/next-question`).then((response: QuestionDto) => {
            console.log(response);
            if (response) {
                setQuestion(response);
            } else {
                navigateToScorePage();
            }
        });
    }

    function submittedSelectedAnswer(selected: string) {
        if (selectedId || answerId) {
            return;
        }
        setSelectedId(selected);
        remoteService.post<SubmissionDto>(`/quiz/submit?quizId=${id}`, {
            questionId: question?.id,
            answerId: selected,
            userId: ''
        }).then((response: SubmissionDto) => {
            console.log(response);
            setAnswerId(response.correctAnswerId);
        });
    }

    if (isLoading || (question == undefined)) {
        return <LoadingPageWithText text={"Your next Question is loading..."} isLoading={true}/>;
    }

    return (
        <Section>
            <Title>Quiz</Title>
            <SubTitle>{question.text}</SubTitle>
            <Grid>
                {question.possibleAnswers.map((possibleAnswer, idx) => (
                    <PossibleAnswer key={idx} possibleAnswer={possibleAnswer}
                                    isSelectedAnswer={possibleAnswer.id === selectedId}
                                    isCorrectAnswer={possibleAnswer.id === answerId}
                                    onSubmit={submittedSelectedAnswer}></PossibleAnswer>
                ))}
            </Grid>
            {answerId && <SubTitle>{(selectedId === answerId) ? "Great" : "Keep trying"}</SubTitle>}
            {answerId && <Button onClick={getQuestion}>Next</Button>}
        </Section>
    );
}