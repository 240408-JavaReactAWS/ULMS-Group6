import React, { SyntheticEvent, useState } from 'react';
import axios from 'axios';

export interface IUser {
    userId: number;
    username: string;
    password: string;
    role: string;
}

function Login(){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [currentUser, setCurrentUser] = useState<IUser>();

    let updateUsername = (e: any) => {
        setUsername((e.target as HTMLInputElement).value);
    }

    let updatePassword = (e: any) => {
        setPassword((e.target as HTMLInputElement).value);
    }

    let login = async() => {
        let res = await axios.post('http://localhost:8080/users/login', {username, password})
            .then((response:any) => {
                localStorage.setItem("username", response.data.username)
                return response.data;})
                .catch( (error:any) => {
                    localStorage.removeItem("username")
                    console.error(error)
                }
            );

        setCurrentUser(res);
    }

    return (
        <main>
            <h1>Login</h1>
            <form>
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" required />
                <label htmlFor="password">Password:</label>
                <input type="password" id="password" name="password" required />
                <button type="submit">Login</button>
            </form>
        </main>
    )
}

export default Login;