import React, { useState, useEffect } from "react";
import axios from "axios";


function Round(props) {


    // this.state = {questionNumber: 0};

    const [questionNumber, setQuestionNumber] = useState(0);
    const [currentQuestion, setQuestion] = useState([]);
    const [lastAnswerValidation, setLastAnswerValidation] = useState(true);
    // let state;
    // state = { testVarible: "this is a test" };


    // onMove = () => {
    //     console.log(this.state.testVarible);
    //     this.setState({ testVarible: "new value" });
    // }


    const submitAnswer = (answer, question) => {
        axios.get("http://localhost:8080/api/v1/match/answer",
            { params: { answer: answer, question_id: question.id, match_id: props.match.id, user_id: props.user.id }}).then(response => {
            console.log("res", response.data);
            setLastAnswerValidation(response.data);
            // setRound(response.data);
            // setPicking(false);
        }, (error) => {
            console.log(console.log(error));
        });
        console.log("x");
    }

    const nextQuestion = () => {
        // console.log(this.questionNumber);
        // this.setState( this.questionNumber = this.questionNumber+1);
        // console.log(this.questionNumber);
    }


    return(
        <div>
           <p>IN Round</p>
           <p>Round for category: {props.round.category.name}</p>
            <p>Question Number {questionNumber+1} / 3</p>
            <p>Question: translate "{props.round.questions[questionNumber].questionString}"</p>
            {<button onClick={() => submitAnswer(props.round.questions[questionNumber].correctAnswer, props.round.questions[questionNumber])}> {props.round.questions[questionNumber].correctAnswer} </button>}
            {<button onClick={() => submitAnswer(props.round.questions[questionNumber].wrongAnswer1, props.round.questions[questionNumber])}> {props.round.questions[questionNumber].wrongAnswer1} </button>}
            {<button onClick={() => submitAnswer(props.round.questions[questionNumber].wrongAnswer2, props.round.questions[questionNumber])}> {props.round.questions[questionNumber].wrongAnswer2} </button>}
            {<button onClick={() => submitAnswer(props.round.questions[questionNumber].wrongAnswer3, props.round.questions[questionNumber])}> {props.round.questions[questionNumber].wrongAnswer3} </button>}
            <p>Your answer was {lastAnswerValidation.toString()} </p>
            <p>{<button onClick={() => setQuestionNumber(questionNumber+1)}> next Question </button>}</p>
        </div>
    );
}

export default Round;