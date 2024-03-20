import SubTitle from "./SubTitle.tsx";
import Section from "./Section.tsx";
import LoadingPage from "./LoadingPage.tsx";
import {useEffect, useState} from "react";

interface Props {
    text: string;
}

export default function LoadingPageWithText
({text}: Props) {

    const [showLoader, setShowLoader] = useState<boolean>(false);

    useEffect(() => {
            setTimeout(() => {
                setShowLoader(true);
            }, 150);
        }
        , []);

    if (!showLoader) return null;

    return (
        <Section>
            <SubTitle>{text}</SubTitle>
            <LoadingPage></LoadingPage>
        </Section>
    );
}
