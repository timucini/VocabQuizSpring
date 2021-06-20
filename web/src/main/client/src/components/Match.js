import React, { useState, useEffect } from "react";
import axios from "axios";
import Round from './Round';


function Match(props) {

    const [playerNumber, setPlayerNumber] = useState();
    const [matchStarted, setMatchStarted] = useState(false);
    const [awaitPlayer, setAwait] = useState(false);
    const [awaitCategoryPicking, setPicking] = useState(false);
    const [currentRound, setRound] = useState([]);
    const [roundStarted, setRoundStarted] = useState(false);
    const [pickingTurn, setPickingTurn] = useState(false);
    const [roundFinished, setRoundFinished] = useState(false);
    const [roundNumber, setRoundNumber] = useState(1);

    const checkPlayerNumber = () => {
        if ( props.user.id == props.match.player1.id) {
            setPlayerNumber(1);
            setPickingTurn(true);
        } else {
            setPlayerNumber(2);
            setAwait(true);
        }
    }

    useEffect(() => {
        checkPlayerNumber();
    }, []);


    const startMatch = () => {
        setMatchStarted(true);
        setPicking(true);
    }

    const pickCategory = (category_id) => {
        console.log("picked Category with id:" + category_id);
        axios.post("http://localhost:8080/api/v1/match/round", null,
        { params: { category_id: category_id, match_id: props.match.id }}).then(response => {
          console.log(response);
          // let props = {data: response.data, match: props.match, user: props.user};
          setRound(response.data);
          setPicking(false);
        }, (error) => {
          console.log(console.log(error));
        });
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
        if (pickingTurn && matchStarted && awaitCategoryPicking && (roundNumber <= 3)) {
            console.log("Need to pick category")
            return categoryList
        }
    }

    const renderAwaitPlayer = () => {
        if ((awaitPlayer || roundFinished) && !pickingTurn) {
            return(
                <div>
                    <p>Await other player</p>
                    {<button onClick={() => updateMatch()}>Update</button>}
                </div>
            )
        }
    }

    const renderStartButton = () => {
        if (playerNumber === 1 && !matchStarted && !awaitPlayer) {
            return (
                <button onClick={() => startMatch()}>StartMatch</button>
            );
        }
    }

    const renderFinishButton = () => {
        if (matchStarted && !awaitPlayer) {
            return (
                <button onClick={() => props.finishMatch()}>Finish Match</button>
            )
        }
    }

    const renderRound = () => {
        // setRoundStarted(true);
        if (matchStarted && !awaitCategoryPicking && (!roundFinished || !awaitPlayer)) {
            return (
                <Round round={currentRound} match={props.match} user={props.user} finishRound={() => finishRound()}/>
            )
        }
    }

    function finishRound() {
        if (roundNumber < 3) {
            if (pickingTurn) {
                setPickingTurn(false);
            } else {
                setPickingTurn(true);
            }
            setRoundFinished(true);
            setPicking(true);
            setRoundNumber(roundNumber+1);
        } else {
            props.finishMatch();
        }

    }

    const checkCategoryButton = () => {
        if (matchStarted && awaitPlayer) {
            return (
                <button onClick={() => updateMatch()}>Check Category is choosen</button>
            )
        }
    }

    const updateMatch = () => {
        axios.get("http://localhost:8080/api/v1/match/match",
            { params: { match_id: props.match.id }}).then(res => {
            setRound(res.data.currentRound);
            setMatchStarted(true);
            setAwait(false);
            setPicking(false);
        });
    };

    return(
        <div>
            <div>hello in match: {props.match.id}</div>
            <div>Book of match: {props.match.book.name}</div>
            <div>Your Player Number: {playerNumber}</div>
            <div>Round Number: {roundNumber} / 3</div>
            {renderCategoryList()}
            {renderAwaitPlayer()}
            {checkCategoryButton()}
            {renderStartButton()}
            {renderFinishButton()}
            {renderRound()}
        </div>
    );
}

export default Match;