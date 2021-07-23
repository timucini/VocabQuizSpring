import React from "react";
import { useForm } from 'react-hook-form';
import axios from "axios";
import '../styling/Forms.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function RegisterForm(props) {

    const { register, formState: { errors }, handleSubmit } = useForm();


    const onSubmit = (data) => {
        axios.post("http://localhost:8080/api/v1/user/add", data).then(response => {
          props.setUserState(response.data);
          props.setLoginState(true);
        }, (error) => {
          toast.warn('Cannot register user, use different username or login', { position: toast.POSITION.TOP_CENTER})
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
        <div>
          <p>Bereits registriert?   <button onClick={() => props.setLoginForm()}>Einloggen</button></p>
        </div>
        <ToastContainer />
      </div>
    )
}

export default RegisterForm;