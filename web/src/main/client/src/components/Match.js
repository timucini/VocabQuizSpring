import React, { useState, useEffect } from "react";
import axios from "axios";


function Match(props) {

    const [playerNumber, setPlayerNumber] = useState();
    const [matchStarted, setMatchStarted] = useState(false);
    const [twoPlayersInmatch, setPlayersInMatch] = useState(false);


    const checkPlayerNumber = () => {
        if ( props.user.id == props.match.player1.id) {
            setPlayerNumber(1);
        } else {
            setPlayerNumber(2);
            setPlayersInMatch(true);
        }
    }

    useEffect(() => {
        checkPlayerNumber();
    }, []);


    const startRound = () => {
        axios.post("http://localhost:8080/api/v1/match/startRound").then(res => {
            console.log(res.data);
        });
    };


    const startMatch = () => {
        setMatchStarted(true);
    }

    const pickCategory = (category_id) => {
        console.log("picked Category with id:" + category_id);
        console.log("TODO");
    }


    const categoryList = props.match.book.categories.map((category,index) => {
        return(
            <div key={index}>
                <p>Category-Id: {category.id}</p>
                <p>Category-Name: {category.name}</p>
                {<button onClick={() => pickCategory(category.id)}>Pick category</button>}
            </div>
        )
    })

    const renderCategoryList = () => {
        if (playerNumber == 1 && matchStarted) {
            console.log("Need to pick category")
            return categoryList
        }
    }

    const renderAwaitPlayer = () => {
        if (playerNumber == 2) {
            return <p>Await other player</p>;
        }
    }

    const renderStartButton = () => {
        if (playerNumber == 1 && !matchStarted) {
            return (
                <button onClick={() => startMatch()}>StartMatch</button>
            );
        }
    }

    const renderFinishButton = () => {
        if (matchStarted) {
            return (
                <button onClick={() => props.finishMatch()}>Finish Match</button>
            )
        }
    }

    return(
        <div>
            <div>hello in match: {props.match.id}</div>
            <div>Book of match: {props.match.book.name}</div>
            <div>Your Player Number: {playerNumber}</div>
            {renderCategoryList()}
            {renderAwaitPlayer()}
            {renderStartButton()}
            {renderFinishButton()}
        </div>
    );
}

export default Match;