import React, { useState, useEffect } from "react";
import './App.css';
import RegisterForm from './components/RegisterForm';
import LoginForm from './components/LoginForm';
import MatchLobby from './components/MatchLobby'

export default function App() {


  const [user, setUser] = useState([]);

  const userName = user.userName;

  const [loggedIn, setLogin] = useState(false);

  const [register, setRegisterForm] = useState(false);

  const [login, setLoginForm] = useState(false);

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

  return (
    <div className="App">
       {loggedIn &&
         <MatchLobby logOut={loginState => logOutUser()} />
        }
        {!loggedIn && !register && !login &&
          <button onClick={() => showLoginForm()}>Einloggen</button>
        }
        {!loggedIn && !register && !login &&
         <button onClick={() => showRegisterForm()}>Registrieren</button>
        }
        {!loggedIn && register &&
          <RegisterForm setLoginState={loginState => setLogin(loginState)} setUserState={userState => setUser(userState)} />
        }
        {!loggedIn && login &&
          <LoginForm setLoginState={loginState => setLogin(loginState)} setUserState={userState => setUser(userState)} />
        }
    </div>
    
  );
}
