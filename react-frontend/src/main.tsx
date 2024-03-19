import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import {HashRouter, Route, Routes} from 'react-router-dom';
import Header from './header/Header';
import {ToastContainer} from 'react-toastify';
import ErrorBoundary from './error/ErrorBoundary';
import ErrorPage from './error/ErrorPage';
import LandingPage from './components/LandingPage.tsx';
import TopicPage from "./components/TopicPage.tsx";
import QuizPage from "./components/QuizPage.tsx";
import ScorePage from "./components/ScorePage.tsx";

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <ToastContainer/>
        <HashRouter>
            <Header/>
            <ErrorBoundary>
                <Routes>
                    <Route path="/" element={<LandingPage/>}/>
                    <Route path="/topic" element={<TopicPage/>}/>
                    <Route path="/quiz/:id" element={<QuizPage/>}/>
                    <Route path="/score/:quizId" element={<ScorePage/>}/>
                    <Route path="*" element={<ErrorPage statusCode={400} errorMessage={'Page not found'}/>}/>
                </Routes>
            </ErrorBoundary>
        </HashRouter>
    </React.StrictMode>
);
