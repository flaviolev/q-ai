import styled from 'styled-components';
import Button from '../shared/Button.tsx';
import Title from "../shared/Title.tsx";
import { useNavigate } from "react-router-dom";


const Section = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export default function LandingPage() {

    const navigate = useNavigate();
    function handleClick() {
        navigate("/topic");
    }

    return (
        <Section>
            <Title>Welcome to Q&Ai</Title>
            <Button onClick={handleClick}>Start Quiz</Button>
        </Section>
    );
}
