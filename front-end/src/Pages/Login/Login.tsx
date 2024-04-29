import React, { SyntheticEvent, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export interface IUser {
    userId: number;
    username: string;
    password: string;
    role: string;
}

function Login(){

    const nav = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    let updateUsername = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }

    let updatePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    let login = async() => {
        try {
            let res = await axios.post('http://localhost:8080/users/login/', {
                username: username,
                password: password },
            );

            console.log(res.data);
            if(res.status === 200){
                nav('/dashboard');
            } 

    } catch (error) {
        alert("Error Detected");
        console.error(error);
    }
}

let FormSubmission = async (e: SyntheticEvent) => {
    e.preventDefault();
    await login();
}


    return (
        <main>
            <h1>Login</h1>
            <form onSubmit={FormSubmission}>
                <label htmlFor="username">Username:</label>
                <input type="text" onChange={updateUsername} id="username" name="username" required />
                <label htmlFor="password">Password:</label>
                <input type="password" onChange={updatePassword} id="password" name="password" required />
                <button type="submit">Login</button>
            </form>
        </main>
    );
}

export default Login;