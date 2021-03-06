import React, { useState, useEffect } from "react";
import axios from "axios";
import '../styling/Match.css';


function CreateMatch(props) {

    const [books, setBooks] = useState([])

    const fetchBooks = () => {
        axios.get("http://localhost:8080/api/v1/vocab/get").then(res => {
            setBooks(res.data);
        });
    };

    
    useEffect(() => {
        fetchBooks();
    }, []);

    const matchList = books.map((book,index) => {
        return(
            <div id="match" key={index}>
                <p>Book-Name: {book.name}</p>
                <p>Translation from: {book.languageFrom}</p>
                <p>Translation to: {book.languageTo}</p>
                <button onClick={() => createMatch(book.id)}>Create with Book</button>
            </div>
        )
    })

    const createMatch = (bookId) => {
        axios.post("http://localhost:8080/api/v1/match/create", null,
        { params: { user_id: props.user.id, book_id: bookId }}).then(response => {
          console.log(response);
          props.setMatchState(response.data);
          props.setCreateState(false);
        }, (error) => {
          console.log(console.log(error));
        });
    }

    return(
        <div>
            <h2>Create Match</h2>
            {matchList}
        </div>
    );
}

export default  CreateMatch;