import React from "react";
import Course from "../../interfaces/CourseInterface";
import { useNavigate, useParams } from "react-router-dom";

function CourseCard(props: Course) {

    const id = props.courseId;

    const navigate = useNavigate();

    let viewCourse = () => {
        navigate(`/courses/${id}`);
    }

    return (
        <div className = 'Course'>
            <h1>{props.courseName}</h1>
            <h2>{props.courseId}</h2>
            <button onClick={viewCourse}>View Course</button>
        </div>
    )
}

export default CourseCard;