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

function App() {
  return (
    <BrowserRouter>
      <Nav/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/assignmentsTeacher" element={<AssignmentTeacher courseId={2} />}/>
        <Route path="/login" element={<Login/>} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/adminDashboard" element={<AdminDashboard />} />
        <Route path="/assignments" element={<Assignments userId={5} courseId={2}/>} /> 
        <Route path="/courses/:courseId/announcements" element={<Announcements/>} />
        <Route path="/courses/:courseId/announcements/new-announcement" element={<NewAnnouncementForm/>} />
        <Route path="/GradesStudent" element={<GradesContainer courseId={5} userId={2} />} />
        <Route path="/GradesTeacher" element={<TeacherGrades />} />
        <Route path="/UserList" element={<UserList />} />
        <Route path="/CourseList" element={<CourseList />} />
        <Route path="/manage-course/:courseId" element={<CourseManage />} />
        <Route path="/courses/:courseId" element={<Courses courseId={2} />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
