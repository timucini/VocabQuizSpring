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


    const correctList = props.round.questions[questionNumber].correctAnswer.stringTo;
    const correct = correctList[Math.floor(Math.random()*correctList.length)];
    
    const answer1List = props.round.questions[questionNumber].wrongAnswer1.stringTo;
    const answer1 =  answer1List[Math.floor(Math.random()* answer1List.length)];
    
    const answer2List = props.round.questions[questionNumber].wrongAnswer2.stringTo;
    const answer2 =  answer2List[Math.floor(Math.random()* answer2List.length)];
    
    const answer3List = props.round.questions[questionNumber].wrongAnswer3.stringTo;
    const answer3 =  answer3List[Math.floor(Math.random()* answer3List.length)];

    return(
        <div>
           <p>IN Round</p>
           <p>Round for category: {props.round.category.name}</p>
            <p>Question Number {questionNumber+1} / 3</p>
            <p>Question: translate "{props.round.questions[questionNumber].questionString}"</p>
            {<button onClick={() => submitAnswer(correct, props.round.questions[questionNumber])}> {correct} </button>}
            {<button onClick={() => submitAnswer(answer1, props.round.questions[questionNumber])}> {answer1} </button>}
            {<button onClick={() => submitAnswer(answer2, props.round.questions[questionNumber])}> {answer2} </button>}
            {<button onClick={() => submitAnswer(answer3, props.round.questions[questionNumber])}> {answer3} </button>}
            <p>Your answer was {lastAnswerValidation.toString()} </p>
            <p>{<button onClick={() => setQuestionNumber(questionNumber+1)}> next Question </button>}</p>
        </div>
    );
}

export default Round;