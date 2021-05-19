import React, { useState, useEffect } from "react";
import { useForm } from 'react-hook-form';
import axios from "axios";

function LoginForm(props) {
    const { register, formState: { errors }, handleSubmit } = useForm();


    const onSubmit = (data) => {
        console.log(data)
        axios.get("http://localhost:8080/api/v1/user/get", { params: { userName: data.userName, password: data.password}}).then(response => {
          console.log(response);
          props.setUserState(response.data);
          props.setLoginState(true);
        }, (error) => {
          console.log(console.log(error));
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
      </div>
    )
}

export default LoginForm;