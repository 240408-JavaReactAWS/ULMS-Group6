import React from 'react';
//import logo from './logo.svg';
import Nav from './Components/Nav/Nav';
import './App.css';
import Login from './Pages/Login/Login';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Assignments from './Components/Assignmnets/Assignments';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login/>} />
        <Route path="/assignments" element={<Assignments/>} /> 
      </Routes>
      <Nav></Nav>
    </BrowserRouter>
  );
}

export default App;
