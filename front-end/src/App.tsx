import React from 'react';
//import logo from './logo.svg';
import Nav from './Components/Nav/Nav';
import './App.css';
import Login from './Pages/Login/Login';
import {BrowserRouter, Routes, Route} from "react-router-dom";

function App() {
  return (
    <>
      <Nav></Nav>
      <Login></Login>
    </>
  );
}

export default App;
