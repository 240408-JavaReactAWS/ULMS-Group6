import React, {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import User from "../../interfaces/UserInterface";
import Course from "../../interfaces/CourseInterface";
import CourseCard from "../../Components/CourseCards/CourseCard";
import axios from "axios";
import './Dashboard.css';

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
                        console.log(user);
                        setCurrentUser(user);
                        if (user.role === 'STUDENT') {
                            const response = await axios('http://localhost:8080/users/' + userId + '/courses');
                            if (response.status === 200) {
                                const courses = response.data; // Use the 'data' property instead of calling 'json()'
                                setCourses(courses);
                            }
                        } else if (user.role === 'TEACHER') {
                            const response = await axios('http://localhost:8080/users/' + userId + '/taught'); // modify for teacher get mapping
                            if (response.status === 200) {
                                const courses = response.data; // Use the 'data' property instead of calling 'json()'
                                setCourses(courses);
                            }
                        }
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

    return (
        <> 
            <div className="dashboard-header-2">
                <h2> Welcome, {curUser?.username}</h2>
            </div>
            <h1 className="course-header"> My Courses </h1>
            <div id="courseList">
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