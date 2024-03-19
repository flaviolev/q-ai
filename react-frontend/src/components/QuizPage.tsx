import styled from 'styled-components';
import {useEffect, useState} from 'react';
import Title from "../shared/Title.tsx";
import LoadingPage from "../shared/LoadingPage.tsx";
import {useParams} from "react-router-dom"
import {QuestionDto, ScoreDto} from "../shared/model.tsx";
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

    const {id} = useParams();

    const [selectedId, setSelectedId] = useState<string | undefined>();
    const [answerId, setAnswerId] = useState<string | undefined>();

    useEffect(() => {
        getQuestion();
    }, []);

    function getQuestion() {
        remoteService.get<QuestionDto>(`/quiz/${id}/next-question`).then((response: QuestionDto) => {
            console.log(response);
            setQuestion(response)
        });
    }

    if (question == undefined) {
        return <LoadingPage/>;
    }

    function submittedSelectedAnswer(selected: string){
        setSelectedId(selected);
        remoteService.post<ScoreDto>(`/quiz/submit?quizId=${id}`, {questionId: question?.id, answerId: selected, userId: ''}).then((response: ScoreDto) => {
            console.log(response);
            const correctAnswerId = response.submissionDtoList.find(s => s.answerId === selectedId)?.correctAnswerId;
            setAnswerId(correctAnswerId);
        });

    }

    return (
        <Section>
            <Title>Quiz</Title>
            <SubTitle>{question.text}</SubTitle>
            <Grid>
                {question.possibleAnswers.map((possibleAnswer, idx) => ("test " + answerId &&
                    <PossibleAnswer key={idx} possibleAnswer={possibleAnswer} isSelectedAnswer={possibleAnswer.id === selectedId} isCorrectAnswer={possibleAnswer.id === answerId} onSubmit={submittedSelectedAnswer}></PossibleAnswer>
                ))}
            </Grid>
        </Section>
    );
}