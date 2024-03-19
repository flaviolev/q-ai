import Title from "../shared/Title.tsx";
import Section from "../shared/Section.tsx";
import {useEffect, useState} from "react";
import remoteService from "../services/RemoteService.tsx";
import {ScoreDto} from "../shared/model.tsx";
import LoadingPage from "../shared/LoadingPage.tsx";
import {useNavigate, useParams} from "react-router-dom";
import Button from "../shared/Button.tsx";


export default function ScorePage() {

    const {id} = useParams();
    const navigate = useNavigate();
    const [score, setScore] = useState<ScoreDto | undefined>(undefined);

    useEffect(() => {
        getScore();
    }, []);

    function getScore() {
        remoteService.get<ScoreDto>(`/quiz/score?quizId=${id}`).then((response: ScoreDto) => {
            const correctCount = response.submissionDtoList.reduce((count, answer) => {
                return count + (answer.answerId === answer.correctAnswerId ? 1 : 0);
            }, 0);
            setScore({...response, correctCount});
        });
    }

    function handleRestartQuiz() {
        navigate("/topic");
    }

    if (score == undefined) {
        return <LoadingPage/>;
    }

    return (
        <Section>
            <Title>Score</Title>
            <h2>{score.correctCount}/{score.numberOfQuestions} correct</h2>
            <Button onClick={handleRestartQuiz}>New Quiz</Button>
        </Section>
    );
}