import React, { SyntheticEvent, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Login.css';

function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    

    const submit = async (e: SyntheticEvent) => {
        e.preventDefault();
        try{
            const response = await axios.post('http://localhost:8080/users/login', {
                username,password});
            if(response.data){
                console.log(response.data);
                //Save username to local storage
                localStorage.setItem('userId', response.data.userId);
                localStorage.setItem('role', response.data.role);

                // Redirect to dashboard based on the role
                if(response.data.role === 'ADMIN'){
                    console.log('Admin login detected. Redirecting to admin dashboard.');
                    navigate('/adminDashboard');
                } else {
                    console.log('Student login detected. Redirecting to student dashboard.');
                    navigate('/dashboard');
                }
            } else{
                setError('Login failed. Please check your username and password.');
                localStorage.clear();
            }
        } catch (error: any) {
            if (error.response) {
                // The server returned an error response
                setError(`Login failed. Please check your username and password.`);
            } else if (error.request) {
                // The request was made but no response was received
                setError('Login failed. The server did not respond.');
            } else {
                // Something else happened while setting up the request
                setError('Something went wrong. Please try again.');
            }

            localStorage.clear();

        }
    }

    return (
        <>
            <div className="login-container"> 
                <div className="logo-container">
                    <h2>Welcome to Unified Learning Management System</h2>
                </div>
                <form className = "login-form" onSubmit={submit}>
                    <h1>Login</h1>
                    {error && <h5 style={{ color: 'Blue', background:"orange" }}>{error}</h5> }
                    <input type="text" required placeholder="Username" onChange={e => setUsername(e.target.value)} />
                    <input type="password" required placeholder="Password" onChange={e => setPassword(e.target.value)} />
                    <button type="submit">Login</button>
                </form>
            </div>
        </> 
    );
}

export default Login;