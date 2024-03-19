import {PossibleAnswerDto} from "../../shared/model.tsx";
import Button from "../../shared/Button.tsx";

interface Props {
    possibleAnswer: PossibleAnswerDto;
}

export default function PossibleAnswer({possibleAnswer}: Props) {

    function handleAnswerSelection(answerId: String) {
        console.log(`selected Answer: ${answerId}`)
    }

    return (
        <Button onClick={() => {
            handleAnswerSelection(possibleAnswer.id)
        }}>{possibleAnswer.text}</Button>
    );
}
