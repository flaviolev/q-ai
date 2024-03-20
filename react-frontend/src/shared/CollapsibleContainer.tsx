import {ReactNode, useState} from 'react';
import styled from 'styled-components';

const Container = styled.div`
  margin: 20px;
`;

const Header = styled.div`
  cursor: pointer;
  padding: 10px;
  background-color: #f0f0f0;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Content = styled.div`
  padding: 20px;
  border-top: 1px solid #ccc;
`;

export interface StyleProps {
    $isOpen: boolean;
}

const Arrow = styled.span.attrs<StyleProps>($props => ({
    'transform': $props.$isopen ? 'rotate(180deg)' : 'rotate(0deg)'
}))<StyleProps>`
  display: inline-block;
  transition: transform 0.2s ease-in-out;
`;

interface Props {
    children: ReactNode;
    text: string
}

export default function CollapsibleContainer({children, text}: Props) {
    const [isOpen, setIsOpen] = useState<boolean>(false);

    const toggleOpen = () => setIsOpen(!isOpen);

    return (
        <Container>
            <Header onClick={toggleOpen}>
                {text}
                <Arrow $isOpen={isOpen.toString()}>▼</Arrow>
            </Header>
            {isOpen && (
                <Content>
                    {children}
                </Content>
            )}
        </Container>
    );
}