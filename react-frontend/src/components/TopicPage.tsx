import styled from 'styled-components';
import remoteService from '../services/RemoteService.tsx';
import {useState} from 'react';
import Title from "../shared/Title.tsx";
import {QuizIdDto} from "../shared/model.tsx";
import {useNavigate} from "react-router-dom";
import TextInput from "../shared/TextInput.tsx";
import Button from "../shared/Button.tsx";
import LoadingPageWithText from "../shared/LoadingPageWithText.tsx";
import MultilineTextInput from "../shared/MultilineTextInput.tsx";
import CollapsibleContainer from "../shared/CollapsibleContainer.tsx";

const Section = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export default function TopicPage() {

    const navigate = useNavigate();

    const [topicText, setTopicText] = useState<string | undefined>(undefined);
    const [optionsText, setOptionsText] = useState<string | undefined>("numberOfQuestions=3\ndifficulty=difficult");
    const [quizId, setQuizId] = useState<QuizIdDto | undefined>({id: ""});

    function createQuiz() {
        setQuizId(undefined)
        remoteService.post<QuizIdDto>(`/quiz?topic=${topicText}`, {optionsText}).then((response: QuizIdDto) => {
            console.log(response);
            setQuizId(response)
            navigateToQuizPage(response)
        });
    }

    function navigateToQuizPage(quizId: QuizIdDto) {
        navigate(`/quiz/${quizId.id}`);
    }

    function handleTextChange(newTopicText: string) {
        setTopicText(newTopicText);
    }

    function handleMultilineTextChange(newOptionsText: string) {
        setOptionsText(newOptionsText);
    }


    function handleKeyDown(event: React.KeyboardEvent<HTMLInputElement>) {
        if (event.key === 'Enter') {
            createQuiz();
        }
    }

    if (quizId == undefined) {
        return <LoadingPageWithText text={"Your quiz gets generated..."}/>;
    }

    return (
        <Section>
            <Title>Topics</Title>
            <TextInput placeholder="Enter topic here..." onChange={handleTextChange} onKeyDown={handleKeyDown}
                       value={topicText}/>
            <CollapsibleContainer text={"Options"}>
                <MultilineTextInput onChange={handleMultilineTextChange} value={optionsText}/>
            </CollapsibleContainer>
            <Button onClick={createQuiz}>Start Quiz with Topic</Button>
        </Section>
    );
}
