import React from 'react';
//import logo from './logo.svg';
import Nav from './Components/Nav/Nav';
import './App.css';
import Login from './Pages/Login/Login';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Assignments from './Components/Assignmnets/Assignments';
import Announcements from './Components/Announcements/Announcements';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login/>} />
        <Route path="/login" element={<Login/>} />
        <Route path="/assignments" element={<Assignments userId={13} courseId={5}/>} /> 
        <Route path="/announcements" element={<Announcements courseId={5}/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
