import React, { useState, useEffect } from "react";
import axios from "axios";


function Match(props) {

    const startRound = () => {
        axios.post("http://localhost:8080/api/v1/match/startRound").then(res => {
            console.log(res.data);
        });
    };

    const categoryList = props.match.book.categories.map((category,index) => {
        return(
            <div key={index}>
                <p>Category-Id: {category.id}</p>
                <p>Category-Name: {category.name}</p>
                {/*<button onClick={() => props.setMatchState(match)}>Joinen</button>*/}
            </div>
        )
    })

    return(
        <div>
            <div>hello in match: {props.match.id}</div>
            <div>Book of match: {props.match.book.name}</div>
            {categoryList}
            <button onClick={() => props.finishMatch()}>Finish Match</button>
        </div>
    );
}

export default Match;