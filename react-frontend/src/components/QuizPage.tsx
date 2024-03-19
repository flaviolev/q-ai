import styled from 'styled-components';
import React, {useEffect, useState} from 'react';
import Title from "../shared/Title.tsx";
import LoadingPage from "../shared/LoadingPage.tsx";
import {useLocation} from "react-router-dom"
import {QuestionDto} from "../shared/model.tsx";
import SubTitle from "../shared/SubTitle.tsx";
import Grid from "../shared/Grid.tsx";
import PossibleAnswer from "./quiz/PossibleAnswer.tsx";
import remoteService from "../services/RemoteService.tsx";

const Section = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export default function QuizPage() {

    const [question, setQuestion] = useState<QuestionDto | undefined>(undefined);

    const {state} = useLocation();
    const {quizId} = state;


    useEffect(() => {
        getQuestion();
    }, []);

    function getQuestion() {
        remoteService.get<QuestionDto>(`/quiz/${quizId.id}/next-question`).then((response: QuestionDto) => {
            console.log(response);
            setQuestion(response)
        });
    }


    if (question == undefined) {
        return <LoadingPage/>;
    }

    return (
        <Section>
            <Title>Quiz</Title>
            <SubTitle>{question.text}</SubTitle>
            <Grid>
                {question.possibleAnswers.map((possibleAnswer, idx) => (
                    <PossibleAnswer key={idx} possibleAnswer={possibleAnswer}></PossibleAnswer>
                ))}
            </Grid>
        </Section>
    );
}