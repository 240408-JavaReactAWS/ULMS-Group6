import React, { useEffect } from "react";
import User from "../../interfaces/UserInterface";
import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import CourseList from "../CourseList/CourseList";
import './AdminDashboard.css';
import UserList from "../UserList/UserList";

function AdminDashboard() {
    // find the user in the database from local storage
    // if the user is not found, redirect to the login page
    // if the user is found, display the user's username
    const navigate = useNavigate();
    const [selectedTab, setSelectedTab] = useState('userlist');

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
          <div className="dashboard-header">
              <h1> Admin Dashboard</h1>
              <h2> Welcome, {curUser?.username}</h2>
          </div>
          <div className="content-box">
            <div className="tab-buttons">
              <button onClick={() => setSelectedTab('userlist')} className="tab-button">User List</button>
              <button onClick={() => setSelectedTab('courselist')} className="tab-button">Course List</button>
            </div>
            {selectedTab === 'userlist' && <UserList />}
            {selectedTab === 'courselist' && <CourseList />}
          </div>
        </>
    )
}

export default AdminDashboard;