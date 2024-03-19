import styled from 'styled-components';

const StyledInput = styled.input`
  background-color: white;
  color: var(--primary);
  padding: 0.5rem 1rem;
  font-size: 16px;
  border-radius: 4px;
  border: 1px solid var(--primary);
  outline: none;
  margin-bottom: 1rem; // Add bottom margin


  &:focus {
    border-color: var(--secondary);
    box-shadow: 0 0 0 2px rgba(0, 0, 0, 0.1);
  }

  @media (max-width: 500px) {
    padding: 0.5rem;
  }
`;

interface Props {
    placeholder?: string;
    onChange: (value: string) => void;
    value?: string;
}

export default function TextInput({placeholder, onChange, value}: Props) {
    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        onChange(event.target.value);
    };

    return (
        <StyledInput
            type="text"
            placeholder={placeholder}
            onChange={handleInputChange}
            value={value}
        />
    );
}
