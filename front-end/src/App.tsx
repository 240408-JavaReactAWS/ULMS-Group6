import React from 'react';
//import logo from './logo.svg';
import Nav from './Components/Nav/Nav';
import './App.css';
import Login from './Pages/Login/Login';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Assignments from './Components/Assignmnets/Assignments';
import AssignmentTeacher from './Components/Assignmnets/AssignmentTeacher';

function App() {
  return (
    <BrowserRouter>
    <Nav></Nav>
      <Routes>
        <Route path="/" element={<Login/>} />
        <Route path="/assignments" element={<Assignments/>} /> 
        <Route path="/assignmentsTeacher" element={<AssignmentTeacher />}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
