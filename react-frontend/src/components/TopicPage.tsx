import styled from 'styled-components';
import Button from '../shared/Button.tsx';
import remoteService from '../services/RemoteService.tsx';
import {useEffect, useState} from 'react';
import Title from "../shared/Title.tsx";
import {QuizIdDto, TopicsDto} from "../shared/model.tsx";
import LoadingPage from "../shared/LoadingPage.tsx";
import {useNavigate} from "react-router-dom";

const Section = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export default function TopicPage() {

    useEffect(() => {
        loadTopics();
    }, []);

    const navigate = useNavigate();

    const [topics, setTopics] = useState<TopicsDto | undefined>(undefined);
    const [quizId, setQuizId] = useState<QuizIdDto | undefined>({id: ""});

    function loadTopics() {
        remoteService.get<TopicsDto>('/quiz/topics').then((response: TopicsDto) => {
            console.log(response);
            setTopics(response)
        });
    }

    function createQuiz(topic: String) {
        setQuizId(undefined)
        remoteService.post<QuizIdDto>(`/quiz?topic=${topic}`).then((response: QuizIdDto) => {
            console.log(response);
            setQuizId(response)
            navigateToQuizPage(response)
        });
    }

    function navigateToQuizPage(quizId: QuizIdDto) {
        navigate(`/quiz/${quizId.id}`);
    }

    if (topics == undefined || quizId == undefined) {
        return <LoadingPage/>;
    }

    return (
        <Section>
            <Title>Topics</Title>
            {topics.topicList.map((topic, idx) => (
                <Button key={idx} onClick={() => createQuiz(topic)}>{topic}</Button>
            ))}
        </Section>
    );
}
