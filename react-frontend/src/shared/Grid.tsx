import styled from 'styled-components';
import {ReactNode} from 'react';

const StyledGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-auto-rows: auto;
  grid-gap: 1rem;
`;

interface Props {
    children: ReactNode;
}

export default function Grid({children}: Props) {
    return (
        <StyledGrid>
            {children}
        </StyledGrid>
    );
}
