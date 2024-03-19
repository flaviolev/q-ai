import styled from 'styled-components';
import {ReactNode} from 'react';

const StyledSection = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

interface Props {
    children: ReactNode;
}

export default function Section({children}: Props) {
    return (
        <StyledSection>
            {children}
        </StyledSection>
    );
}
