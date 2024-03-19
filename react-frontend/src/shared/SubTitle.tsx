import styled from 'styled-components';
import {ReactNode} from "react";

const StyledSubTitle = styled.h4`
  font-size: 2rem;
  margin-bottom: 0;
  color: var(--primary);
  padding-bottom: 4%;
`;

interface Props {
    children: ReactNode;
}

export default function SubTitle({children}: Props) {
    return (
        <StyledSubTitle>{children}</StyledSubTitle>
    );
}