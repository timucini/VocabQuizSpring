import React, { useState, useEffect } from "react";
import axios from "axios";


const AvailableMatches = () => {
    const [matches, setMatches] = useState([]);

    const fetchAvailableMatches = () => {
        axios.get("http://localhost:8080/api/v1/match/matches").then(res => {
            setMatches(res.data);
        });
    };

    useEffect(() => {
        fetchAvailableMatches();
    }, []);

    return matches.map((match,index) => {
        return(
            <div key={index}>
                <p>Match-Id: {match.id}</p>
                <p>Player in Match: {match.player1.userName}</p>
                <p>Book: {match.book.name}</p>
            </div>
        )
    })
};

    function MatchLobby(props) {
    return(
        <div className = "MatchLobby">
            <AvailableMatches />
            <button onClick={() => props.logOut()}>Ausloggen</button>
        </div>
    )
}

export default MatchLobby;