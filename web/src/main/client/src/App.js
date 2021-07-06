import React, { useState } from "react";
import './App.css';
import RegisterForm from './components/RegisterForm';
import LoginForm from './components/LoginForm';
import MatchLobby from './components/MatchLobby';
import Match from './components/Match';
import Result from './components/Result';
import CreateMatch from './components/CreateMatch';


export default function App() {


  const [user, setUser] = useState([]);

  const [loggedIn, setLogin] = useState(false);

  const [register, setRegisterForm] = useState(false);

  const [login, setLoginForm] = useState(false);

  const [match, setMatch] = useState([]);

  const [mactchInProgress, setMatchInProgress] = useState(false);

  const [matchfinished, setMatchFinished] = useState(false);

  const [createMatch, setCreateMatch] = useState(false);

  function showLoginForm() {
    setLoginForm(true);
    setRegisterForm(false);
  }

  function showRegisterForm() {
    setLoginForm(false);
    setRegisterForm(true);
  }

  function logOutUser() {
    setLogin(false);
    setUser([]);
    setRegisterForm(false);
    setLoginForm(false);
  }

  function startMatch(matchState) {
    console.log("Matchstate;")
    console.log(matchState)
    setMatch(matchState);
    setMatchInProgress(true);
  }

  function finishMatch() {
    setMatchFinished(true);
  }

  function endMatch() {
    setMatchFinished(false);
    setMatchInProgress(false);
    setMatch([]);
  }

  return (
    <div className="App">
        {loggedIn && !mactchInProgress && !createMatch &&
         <MatchLobby user={user} logOut={() => logOutUser()} setMatchState={matchstate => startMatch(matchstate)} setCreateState={createState => setCreateMatch(createState)} />         
        }
        {loggedIn && !mactchInProgress && createMatch &&
         <CreateMatch user={user} logOut={() => logOutUser()} setMatchState={matchstate => startMatch(matchstate) } setCreateState={createState => setCreateMatch(createState)} />         
        }
        {loggedIn && mactchInProgress && !matchfinished &&
          <Match match={match} user={user} finishMatch={() => finishMatch()}/>
        }
        {loggedIn && mactchInProgress && matchfinished &&
          <Result match={match} endMatch={() => endMatch()}/>
        }
        <div id="formContrainer">
          {!loggedIn && !register && !login &&
            <button id="loginBtn" onClick={() => showLoginForm()}>Einloggen</button>
          }
          {!loggedIn && !register && !login &&
            <button id="registerBtn" onClick={() => showRegisterForm()}>Registrieren</button>
          }
          {!loggedIn && register &&
            <RegisterForm setLoginForm={() => showLoginForm()} setLoginState={loginState => setLogin(loginState)} setUserState={userState => setUser(userState)} />
          }
          {!loggedIn && login &&
            <LoginForm setRegisterForm={() => showRegisterForm()} setLoginState={loginState => setLogin(loginState)} setUserState={userState => setUser(userState)} />
          }
        </div>
    </div>
    
  );
}
