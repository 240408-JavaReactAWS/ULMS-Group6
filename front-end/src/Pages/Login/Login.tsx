import React, { SyntheticEvent, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    localStorage.clear();

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
            setError('Something went wrong. Please try again.');
            localStorage.clear();
        }
    }

    return (
        <form onSubmit={submit}>
            <h1>Login</h1>
            {error && <h5 style={{ color: 'red' }}>{error}</h5> }
            <input type="text" required placeholder="Username" onChange={e => setUsername(e.target.value)} />
            <input type="password" required placeholder="Password" onChange={e => setPassword(e.target.value)} />
            <button type="submit">Login</button>
        </form>
    );
}

export default Login;