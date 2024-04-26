import React from 'react';
//import logo from './logo.svg';
import Nav from './Components/Nav/Nav';
import './App.css';
import Login from './Pages/Login/Login';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import GradesContainer from './Components/GradesContainer/GradesContainer';
import TeacherGrades from './Components/TeacherGrades/TeacherGrades';
import UserList from './Pages/UserList/UserList';
import CourseList from './Pages/CourseList/CourseList';
import CourseManage from './Pages/CourseManage/CourseManage';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/GradesStudent" element={<GradesContainer />} />
          <Route path="/GradesTeacher" element={<TeacherGrades />} />
          <Route path="/UserList" element={<UserList />} />
          <Route path="/CourseList" element={<CourseList />} />
          <Route path="/manage-course/:courseId" element={<CourseManage />} />
        </Routes>
      </BrowserRouter>
      </>
      
  
  );
}

export default App;
