import styled from 'styled-components';
import Button from '../shared/Button.tsx';
import remoteService from '../services/RemoteService.tsx';
import {useEffect, useState} from 'react';
import Title from "../shared/Title.tsx";
import {TopicsDto} from "../shared/model.tsx";
import LoadingPage from "../shared/LoadingPage.tsx";

const Section = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export default function TopicPage() {
    function handleClick() {
    }

    useEffect(() => {
        loadTopics();
    }, []);

    const [topics, setTopics] = useState<TopicsDto | undefined>(undefined);

    function loadTopics() {
        remoteService.get<TopicsDto>('/quiz/topics').then((response: TopicsDto) => {
            console.log(response);
            setTopics(response)
        });
    }

    if (topics == undefined) {
        return <LoadingPage/>;
    }

    return (
        <Section>
            <Title>Topics</Title>
            {topics.topicList.map((topic, idx) => (
                <Button key={idx} onClick={handleClick}>{topic}</Button>
            ))}
        </Section>
    );
}
