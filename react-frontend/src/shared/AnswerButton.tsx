import styled from 'styled-components';
import {ReactNode} from 'react';

export enum ButtonStyle {
    INITIAL = "var(--secondary)",
    SELECTED = "orange",
    CORRECT = "green",
}

export interface StyleProps {
    buttonbackgroundcolor: ButtonStyle;
}

const StyledButton = styled.button<StyleProps>`
  background-color: ${props => props.buttonbackgroundcolor.valueOf()};
  font-weight: bold;
  color: white;
  padding: 1rem 2rem;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
  border-radius: 4px;
  border: 1px solid var(--secondary);

  &:hover {
    color: ${props => props.buttonbackgroundcolor !== ButtonStyle.INITIAL && "white" || "var(--secondary)"};
    background-color: ${props => props.buttonbackgroundcolor !== ButtonStyle.INITIAL && props.buttonbackgroundcolor.valueOf() || "white"};
  }

  @media (max-width: 500px) {
    padding: 1rem;
  }
`;

interface Props {
    children: ReactNode;
    onClick: () => void;
    isSelectedAnswer: boolean | undefined
    isCorrectAnswer: boolean | undefined
}

export default function AnswerButton({children, onClick, isSelectedAnswer, isCorrectAnswer}: Props) {
    let buttonStyle;

    if (isCorrectAnswer) {
        buttonStyle = ButtonStyle.CORRECT;
    } else if (isSelectedAnswer) {
        buttonStyle = ButtonStyle.SELECTED;
    } else {
        buttonStyle = ButtonStyle.INITIAL;
    }

    return (
        <StyledButton onClick={onClick} buttonbackgroundcolor={buttonStyle}>
            {children}
        </StyledButton>
    );
}
