import React, { useEffect, useState } from 'react';
import GradesContainer from '../../Components/GradesContainer/GradesContainer'
import Assignments from '../../Components/Assignmnets/Assignments';
import axios from 'axios';
import './CoursePages.css'
import { useParams } from 'react-router-dom';
import AssignmentTeacher from '../../Components/Assignmnets/AssignmentTeacher';
import TeacerGrades from '../../Components/TeacherGrades/TeacherGrades';
import Announcements from '../../Components/Announcements/Announcements';

interface Announcement {
    message: string;
    date: any;
  }



export default function Courses() {
  const courseId = useParams<{ courseId: string }>().courseId;
  const [selectedTab, setSelectedTab] = useState('announcements');
  const [announcements, setAnnouncements] = useState<Announcement[]>([]);

  const [courseName, setCourseName]= useState('');

  useEffect(() => {
    axios.get(`http://localhost:8080/courses/${courseId}`)
      .then(response => {
        setCourseName(response.data.courseName);
      })
      .catch(error => {
        console.error('Error fetching Courses', error);
      });
  }, [courseId]);
  
  useEffect(() => {
    axios.get(`http://localhost:8080/courses/${courseId}/announcements`)
      .then(response => {
        setAnnouncements(response.data);
      })
      .catch(error => {
        console.error('Error fetching announcements', error);
      });
  }, [courseId]);

  return (
    <div className="course-page">
        <h1 className='course-name'>{courseName}</h1>
      <div className="tab-buttons">
        <button onClick={() => setSelectedTab('announcements')} className="tab-button">Announcements</button>
        <button onClick={() => setSelectedTab('assignments')} className="tab-button">Assignments</button>
        <button onClick={() => setSelectedTab('grades')} className="tab-button">Grades</button>
      </div>
      <div className="content"> 
      {/* {selectedTab === 'announcements' && (
        <div className="announcement-list">
          {announcements.map((announcement, index) => (
            <div key={index} className="announcement-card">
              <h3 className="announcement-title">{announcement.message}</h3>
              <p className="announcement-date">Date: {announcement.date}</p>
            </div>
          ))}
        </div>
      )} */}
      {selectedTab === 'announcements' && <Announcements />}
      {selectedTab === 'assignments' && (localStorage.getItem('role') === 'TEACHER' ? <AssignmentTeacher /> : <Assignments/>)}
      {selectedTab === 'grades' && (localStorage.getItem('role') === 'TEACHER' ? <TeacerGrades /> : <GradesContainer/>)}
    </div>
    </div>
  );
}