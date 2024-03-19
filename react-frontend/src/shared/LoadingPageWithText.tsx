import SubTitle from "./SubTitle.tsx";
import Section from "./Section.tsx";
import LoadingPage from "./LoadingPage.tsx";

interface Props {
    text: string;
}

export default function LoadingPageWithText
({text}: Props) {
    return (
        <Section>
            <SubTitle>{text}</SubTitle>
            <LoadingPage></LoadingPage>
        </Section>
    );
}
