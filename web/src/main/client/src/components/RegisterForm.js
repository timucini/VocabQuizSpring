import React, { useState, useEffect } from "react";
import { useForm } from 'react-hook-form';
import axios from "axios";

function RegisterForm(props) {
    const { register, formState: { errors }, handleSubmit } = useForm();


    const onSubmit = (data) => {
        console.log(data)
        axios.post("http://localhost:8080/api/v1/user/add", data).then(response => {
          console.log(response);
          props.setUserState(response.data);
          props.setLoginState(true);
        }, (error) => {
          console.log(console.log(error));
        });
    };
    

    return(
        <div className = "RegisterForm">
        <form onSubmit={handleSubmit(onSubmit)}>
          <input type="text" placeholder="Username" {...register('userName', { required: true })} />
          <input type="text" placeholder="Password" {...register('password', { required: true, minLength: 2 })} />
          {errors.password && "Password is invalid"}
          <input type="submit" value="Profil erstellen" />
        </form>
      </div>
    )
}

export default RegisterForm;