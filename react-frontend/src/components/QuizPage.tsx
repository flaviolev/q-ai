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
import Countdown from "react-countdown";
import LoadingPageWithText from "../shared/LoadingPageWithText.tsx";

const Section = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;
export default function QuizPage() {

    const navigate = useNavigate();

    const [question, setQuestion] = useState<QuestionDto | undefined>(undefined);

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
            if (response) {
                setQuestion(response);
            } else {
                navigateToScorePage();
            }
        });
    }

    function submittedSelectedAnswer(selected: string) {
        submission(selected);
    }

    const onTimerComplete = () => {
        submission(undefined);
    }

    function submission(submissionId: string|undefined){
        if (selectedId || answerId) {
            return
        }
        setSelectedId(submissionId);
        remoteService.post<SubmissionDto>(`/quiz/submit?quizId=${id}`, {
            questionId: question?.id,
            answerId: submissionId,
            userId: ''
        }).then((response: SubmissionDto) => {
            setAnswerId(response.correctAnswerId);
        });
    }

    if (question == undefined) {
        return <LoadingPageWithText text={"Your next Question is loading..."}/>;
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
            <Countdown key={answerId} date={Date.now() + 10000} onComplete={onTimerComplete}
                       renderer={props => !answerId && <SubTitle>{props.seconds}</SubTitle>}
            />
            {answerId && <SubTitle>{(selectedId === answerId) ? "Great" : selectedId ? "Keep trying": "Time is up, keep trying"}</SubTitle>}
            {answerId && <Button onClick={getQuestion}>Next</Button>}
        </Section>
    );
}