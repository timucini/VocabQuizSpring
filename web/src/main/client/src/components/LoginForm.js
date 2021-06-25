import React, { useState, useEffect } from "react";
import { useForm } from 'react-hook-form';
import axios from "axios";
import '../styling/Forms.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function LoginForm(props) {
    const { register, formState: { errors }, handleSubmit } = useForm();


    const onSubmit = (data) => {
        console.log(data)
        axios.get("http://localhost:8080/api/v1/user/get", { params: { userName: data.userName, password: data.password}}).then(response => {
          console.log(response);
          props.setUserState(response.data);
          props.setLoginState(true);
        }, (error) => {
          toast.warn('Cannot login user, please enter valid input', { position: toast.POSITION.TOP_CENTER})
        });
      };


    return(
        <div className = "LoginForm">
        <form onSubmit={handleSubmit(onSubmit)}>
          <input type="text" placeholder="Username" {...register('userName', { required: true })} />
          <input type="text" placeholder="Password" {...register('password', { required: true, minLength: 2 })} />
          {errors.password && "Password is invalid"}
          <input type="submit" value="Einloggen" />
        </form>
        <div>
          <p>Noch kein Konto vorhanden?   <button onClick={() => props.setRegisterForm()}>Registrieren</button></p>
        </div>
        <ToastContainer />
      </div>
    )
}

export default LoginForm;