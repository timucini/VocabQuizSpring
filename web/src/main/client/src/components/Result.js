import React, { useState, useEffect } from "react";
import axios from "axios";


function Result(props) {

    return(
        <div>
            <div>Result for Match with id: {props.match.id}</div>
            <button onClick={() => props.endMatch()}>End Match</button>
        </div>
    );
}

export default Result;