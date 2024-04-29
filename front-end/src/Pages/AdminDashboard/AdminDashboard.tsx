import React, { useEffect } from "react";
import User from "../../Interfaces/UserInterface";
import { useState } from "react";
import { useNavigate } from 'react-router-dom';

function AdminDashboard() {
    // find the user in the database from local storage
    // if the user is not found, redirect to the login page
    // if the user is found, display the user's username
    const navigate = useNavigate();

    const [curUser, setCurrentUser] = useState<User | null>();

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
  },[]) 

    return (
        <>
            <h1> Admin Dashboard</h1>
            <h2> Welcome, {curUser?.username}</h2>
        </>
    )
}

export default AdminDashboard;