import React, { useState, useEffect } from "react";
import { useForm } from 'react-hook-form';
import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../styling/Match.css';

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
            console.log(error);
            console.log(error.response);
          toast.warn('Cannot join match', { position: toast.POSITION.TOP_CENTER})
        });
    };

    const { register, formState: { errors }, handleSubmit } = useForm();


    const onSubmit = (data) => { 
        console.log(data)
        const formData = new FormData();
        formData.append("file", data.file[0]);
        console.log(formData);
        axios.post("http://localhost:8080/api/v1/vocab/upload", formData).then(response => {
            console.log(response);
            console.log("uploaded successfully")
        }, (error) => {
            toast.warn('Cannot add file', { position: toast.POSITION.TOP_CENTER})
        });
    }

    
    const matchList = matches.map((match,index) => {
        return(
            <div id="match" key={index}>
                <p>Match-Id: {match.id}</p>
                <p>Player in Match: {match.player1.userName}</p>
                <p>Book: {match.book.name}</p>
                <button onClick={() => joinMatch(match.id)}>Joinen</button>
            </div>
        )
    });

    return(
        <div className = "MatchLobby">
            <div id="userContainer">Hello {props.user.userName}
            <button onClick={() => props.logOut()}>Ausloggen</button>
            </div>
            <hr/>
            <h3>Matches in Lobby</h3>
            <div id="matchList">
            {matchList}
            </div>
            <button id="createBtn" onClick={() => createMatch()}>Create Match</button>
            <hr/>
            <h4>Upload file</h4>
            <form onSubmit={handleSubmit(onSubmit)} encType="multipart/form-data">
                <input type="file" name="file" {...register('file', { required: true })} />
                {errors.file && "file is invalid"}
            <input type="submit" value="uploaden" />
            </form>
            <ToastContainer />
        </div>
    )
}

export default MatchLobby;