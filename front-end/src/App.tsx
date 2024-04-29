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
import Assignments from './Components/Assignmnets/Assignments';
import AssignmentTeacher from './Components/Assignmnets/AssignmentTeacher';
import Announcements from './Components/Announcements/Announcements';
import NewAnnouncementForm from './Components/Announcements/NewAnnouncementForm';
import Dashboard from './Pages/Dashboard/Dashboard';
import Courses from './Pages/CoursePages/Courses';
import AdminDashboard from './Pages/AdminDashboard/AdminDashboard';
import Home from './Pages/Home/Home';
import Role from './interfaces/RoleInterface';

function App() {
  return (
    <BrowserRouter>
      <Nav/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login/>} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/adminDashboard" element={<AdminDashboard />} />
        <Route path="/courses/:courseId/assignments" element= {localStorage.getItem("userRole") == Role.teacher ? <AssignmentTeacher/> : <Assignments/> } />
        <Route path="/courses/:courseId/announcements" element={<Announcements/>} />
        <Route path="/courses/:courseId/announcements/new-announcement" element={<NewAnnouncementForm/>} />
        <Route path="/courses/:courseId/grades" element={localStorage.getItem("userRole") == Role.teacher ? <TeacherGrades /> : <GradesContainer/>} />
        <Route path="/adminDashboard/UserList" element={<UserList />} />
        <Route path="/adminDashboard/CourseList" element={<CourseList />} />
        <Route path="/manage-course/:courseId" element={<CourseManage />} />
        <Route path="/courses/:courseId" element={<Courses />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
