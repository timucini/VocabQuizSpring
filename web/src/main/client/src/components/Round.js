import React, { useState } from "react";
import axios from "axios";
import {toast} from "react-toastify";

function Round(props) {



    const [questionNumber, setQuestionNumber] = useState(0);
    const [lastAnswerValidation, setLastAnswerValidation] = useState(true);
    const [roundFinished, setRoundFinished] = useState(false);
    const [questionDisabled, setQuestionDisabled] = useState(false);

    const submitAnswer = (answer, question) => {
        axios.get("http://localhost:8080/api/v1/match/answer",
            { params: { answer: answer, question_id: question.id, match_id: props.match.id, user_id: props.user.id }}).then(response => {
            setLastAnswerValidation(response.data);
            setQuestionDisabled(true);
        }, (error) => {
            toast.warn('Answer was not submitted', { position: toast.POSITION.TOP_CENTER})
        });
    };


    const answer1List = props.round.questions[questionNumber].answer1.stringTo;
    const answer1 = answer1List[Math.floor(Math.random()*answer1List.length)];
    
    const answer2List = props.round.questions[questionNumber].answer2.stringTo;
    const answer2 =  answer2List[Math.floor(Math.random()* answer2List.length)];
    
    const answer3List = props.round.questions[questionNumber].answer3.stringTo;
    const answer3 =  answer3List[Math.floor(Math.random()* answer3List.length)];
    
    const answer4List = props.round.questions[questionNumber].answer4.stringTo;
    const answer4 =  answer4List[Math.floor(Math.random()* answer4List.length)];

    const nextQuestion = (questionNumber) => {
        if (questionNumber < 1) {
            setQuestionNumber(questionNumber + 1)
            setQuestionDisabled(false);
        } else {
            setQuestionNumber(questionNumber + 1)
            setQuestionDisabled(false);
            setRoundFinished(true);
        }
    }
    const renderFinishButton = () => {
        if (roundFinished) {
            return (
                <button onClick={() => props.finishRound()}>Finish Round</button>
            )
        }
    }

    return(
        <div>
           <p>IN Round</p>
           <p>Round for category: {props.round.category.name}</p>
            <p>Question Number {questionNumber+1} / 3</p>
            <p>Question: translate "{props.round.questions[questionNumber].questionString}"</p>
            {<button disabled={questionDisabled} onClick={() => submitAnswer(answer1, props.round.questions[questionNumber])}> {answer1} </button>}
            {<button disabled={questionDisabled} onClick={() => submitAnswer(answer2, props.round.questions[questionNumber])}> {answer2} </button>}
            {<button disabled={questionDisabled} onClick={() => submitAnswer(answer3, props.round.questions[questionNumber])}> {answer3} </button>}
            {<button disabled={questionDisabled} onClick={() => submitAnswer(answer4, props.round.questions[questionNumber])}> {answer4} </button>}
            <p>Your answer was {lastAnswerValidation.toString()} </p>
            {!roundFinished &&
                <p>{<button onClick={() => nextQuestion(questionNumber)}> next Question </button>}</p>
            }
            {renderFinishButton()}
        </div>
    );
}

export default Round;