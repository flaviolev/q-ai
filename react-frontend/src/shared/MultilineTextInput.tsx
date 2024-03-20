import styled from 'styled-components';
import {ChangeEvent} from 'react';

const StyledTextArea = styled.textarea`
  background-color: white;
  color: var(--primary);
  padding: 1rem;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid var(--secondary);
  outline: none;
  height: 100px; // Initial height, but the user can resize it

  &:focus {
    border-color: var(--secondary-dark); // Adjust as per your color scheme
    box-shadow: 0 0 0 2px rgba(0, 0, 0, 0.1);
  }

  @media (max-width: 700px) {
    padding: 0.5rem;
  }
`;

interface Props {
    placeholder?: string;
    onChange: (value: string) => void;
    value?: string;
}

export default function MultilineTextInput({placeholder, onChange, value}: Props) {
    const handleInputChange = (event: ChangeEvent<HTMLTextAreaElement>): void => {
        onChange(event.target.value);
    };

    return (
        <StyledTextArea
            placeholder={placeholder}
            onChange={handleInputChange}
            value={value}
        />
    );
}