import React, { useState } from "react";
import axios from "axios";


function Result(props) {

    const [match, setMatch] = useState(props.match);

    const getMatch = () => {
        axios.get("http://localhost:8080/api/v1/match/match",
            { params: { match_id: props.match.id }}).then(response => {
            setMatch(response.data);
        }, (error) => {
            console.log(console.log(error));
        });
    }
    const renderScore = () => {
        // getMatch();
            return (
                <div>Player1 {props.scores[0]} : {props.scores[1]} Player2</div>
            )
    }

    return(
        <div>
            <div>Result for Match with id: {props.match.id}</div>
            {renderScore()}
            {/*<div>Player1 {match.scorePlayer1} : {match.scorePlayer2} Player2</div>*/}
            <button onClick={() => props.endMatch()}>End Match</button>
        </div>
    );
}

export default Result;