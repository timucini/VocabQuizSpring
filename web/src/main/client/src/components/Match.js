import React, { useState, useEffect } from "react";
import axios from "axios";


function Match(props) {

    return(
        <div>
            <div>hello in match: {props.match.id}</div>
            <div>Book of match: {props.match.book.name}</div>
            <button onClick={() => props.finishMatch()}>Finish Match</button>
        </div>
    );
}

export default Match;