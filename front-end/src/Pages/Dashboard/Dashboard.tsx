import React, {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import User from "../../Interfaces/UserInterface";

function Dashboard() {
    const [curUser, setCurrentUser] = useState<User | null>();
    const navigate = useNavigate();

    useEffect(() => {
        let asyncCall = async () => {
            try {
                let userId = localStorage.getItem("userId");
                if (userId) {
                    const response = await fetch('http://localhost:8080/users/' + userId);
                    if (response.ok) {
                        const user = await response.json();
                        setCurrentUser(user);
                    } else {
                        navigate('/login');
                    }
                } else {
                    navigate('/login');
                }
            } catch (error) {
                console.error(error);
            }
        }

        asyncCall();
    },[]);

    return (
        <>
            <h1> {curUser?.role === 'TEACHER'? 'Teacher Dashboard': 'Student Dashboard'}</h1>
            <h2> Welcome, {curUser?.username}</h2>
            <button onClick={() => {
                localStorage.clear();
                navigate('/login');
            }}>Logout</button>
        </>
    )
}

export default Dashboard;