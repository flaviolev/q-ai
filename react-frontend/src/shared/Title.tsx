import styled from 'styled-components';
import {ReactNode} from "react";

const StyledTitle = styled.h3`
  font-size: 4rem;
  margin-bottom: 0;
  color: var(--secondary);
  padding-bottom: 4%;
`;

interface Props {
    children: ReactNode;
}

export default function Title({ children }: Props) {
    return (
        <StyledTitle>{children}</StyledTitle>
    );
}