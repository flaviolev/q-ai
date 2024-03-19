import {PossibleAnswerDto} from "../../shared/model.tsx";
import AnswerButton from "../../shared/AnswerButton.tsx";

interface Props {
    possibleAnswer: PossibleAnswerDto;
    onSubmit: (answerId: string) => void
    isSelectedAnswer: boolean | undefined
    isCorrectAnswer: boolean | undefined
}

export default function PossibleAnswer({possibleAnswer, onSubmit, isSelectedAnswer, isCorrectAnswer}: Props) {
    function handleAnswerSelection(answerId: string) {
        console.log(`selected Answer: ${answerId}`)
        onSubmit(answerId);
    }

    return (
        <AnswerButton isSelectedAnswer={isSelectedAnswer} isCorrectAnswer={isCorrectAnswer} key={possibleAnswer.id} onClick={() => {
            handleAnswerSelection(possibleAnswer.id)
        }}>{possibleAnswer.text}</AnswerButton>
    );
}
