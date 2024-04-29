import React, {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import User from "../../interfaces/UserInterface";
import Course from "../../interfaces/CourseInterface";
import CourseCard from "../../Components/CourseCards/CourseCard";
import axios from "axios";

function Dashboard() {
    const [curUser, setCurrentUser] = useState<User | null>();
    const [courses, setCourses] = useState<Course[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        let asyncCall = async () => {
            try {
                let userId = localStorage.getItem("userId");
                if (userId) {
                    const response = await axios('http://localhost:8080/users/' + userId);
                    if (response.status === 200) {
                        const user = await response.data;
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
    },[]);

    useEffect(() => {
        let asyncCall = async () => {
            try {
                let userId = localStorage.getItem("userId");
                if (userId && curUser?.role === 'STUDENT') {
                    const response = await axios('http://localhost:8080/users/' + userId + '/courses');
                    if (response.status === 200) {
                        const courses = response.data; // Use the 'data' property instead of calling 'json()'
                        setCourses(courses);
                    }
                } else {
                    const response = await axios('http://localhost:8080/users/' + userId + '/courses'); // modify for teacher get mapping
                    if (response.status === 200) {
                        const courses = response.data; // Use the 'data' property instead of calling 'json()'
                        setCourses(courses);
                    }
                }
        } catch (error) {
            console.error(error);
            }
        } 
        asyncCall();
    },[]);

    return (
        <> 
            <h1> {curUser?.role === 'TEACHER'? 'Teacher Dashboard': 'Student Dashboard'}</h1>
            <h2> Welcome, {curUser?.username}</h2>

            <div id="courseList">
                <h3> My Courses </h3>
                {courses.map((course) => {
                    return (
                        <CourseCard key={course.courseId} {...course}/>
                    )
                })}
            </div>
        </>
    )
}

export default Dashboard;