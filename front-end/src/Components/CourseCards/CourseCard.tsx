import React from "react";
import { ICourse } from "../Models/ICourse";

function CourseCard(props: ICourse) {
    return (
        <div className = 'Course'>
            <h1>{props.courseName}</h1>
            <h2>{props.courseId}</h2>
        </div>
    )
}

export default CourseCard;