import Button from '../shared/Button.tsx';
import Title from "../shared/Title.tsx";
import { useNavigate } from "react-router-dom";
import React from "react";
import Section from "../shared/Section.tsx";

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
