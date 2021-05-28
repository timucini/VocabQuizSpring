import React, { useState, useEffect } from "react";
import axios from "axios";

function MatchLobby(props) {

    const [matches, setMatches] = useState([]);

    const fetchAvailableMatches = () => {
        axios.get("http://localhost:8080/api/v1/match/matches",
        { params: { user_id: props.user.id }}).then(res => {
            setMatches(res.data);
        });
    };

    
    useEffect(() => {
        fetchAvailableMatches();
    }, []);

    const createMatch = () => {
        props.setCreateState(true);
    }

    const joinMatch = (matchId) => {
        console.log(matchId);
        axios.post("http://localhost:8080/api/v1/match/join", null,
        { params: { user_id: props.user.id, match_id: matchId }}).then(response => {
          console.log(response);
          props.setMatchState(response.data);
        }, (error) => {
          console.log(console.log(error));
        });
    };

    
    const matchList = matches.map((match,index) => {
        return(
            <div key={index}>
                <p>Match-Id: {match.id}</p>
                <p>Player in Match: {match.player1.userName}</p>
                <p>Book: {match.book.name}</p>
                <button onClick={() => joinMatch(match.id)}>Joinen</button>
            </div>
        )
    })

    return(
        <div className = "MatchLobby">
            <div>Hello {props.user.userName}</div>
            {matchList}
            <button onClick={() => props.logOut()}>Ausloggen</button>
            <p>---------------</p>
            <button onClick={() => createMatch()}>Create Match</button>
        </div>
    )
}

export default MatchLobby;