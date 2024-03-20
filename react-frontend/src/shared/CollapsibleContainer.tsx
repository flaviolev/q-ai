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

const Arrow = styled.span<{ isOpen: boolean }>`
  display: inline-block;
  transition: transform 0.2s ease-in-out;
  transform: ${({isOpen}) => (isOpen ? 'rotate(180deg)' : 'rotate(0deg)')};
`;

interface Props {
    children: ReactNode;
    text: string
}

export default function CollapsibleContainer({children, text}: Props) {
    const [isOpen, setIsOpen] = useState(false);

    const toggleOpen = () => setIsOpen(!isOpen);

    return (
        <Container>
            <Header onClick={toggleOpen}>
                {text}
                <Arrow isOpen={isOpen}>▼</Arrow>
            </Header>
            {isOpen && (
                <Content>
                    {children}
                </Content>
            )}
        </Container>
    );
}