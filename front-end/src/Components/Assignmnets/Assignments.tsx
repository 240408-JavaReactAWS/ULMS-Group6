import { useEffect, useState } from 'react'
import axios from 'axios';
import { error } from 'console';
import './Assignments.css';

export interface Assignment {
    assignmentName: string;
    deadline: any;
}

interface AssignmentsProps {
    userId: number,
    courseId: number
}

function Assignments({userId, courseId}: AssignmentsProps) {
    const [assignments, setAssignments] = useState<Assignment[]>([]);

    useEffect(() => {
    // axios.get('http://localhost:8080/users/2/courses/1/assignments').then(response => {
            // console.log(response.data);
        axios.get(`http://localhost:8080/users/${userId}/courses/${courseId}/assignments`).then(response => {
            console.log(response.data);
            setAssignments(response.data);
        }).catch(error => {
            console.log('Errors retriving Assignments', error);
        });
    }, []);

    return (
        <>
            <div>
                {/* {(() => {
                    console.log(Assignments);
                    return null;
                })()} */}
                {
                    assignments.map((assignment: Assignment, index: number) => (
                        <div key={index} className="assignment-block">
                            <h2>Assignment: {assignment.assignmentName}</h2>
                            <p>Due Date: {assignment.deadline}</p>
                        </div>
                    ))
                }
                <h1 className = "title">Assignments</h1>
                <div className="assignment-container">
                    {assignments.map((assignment, index) => {
                        return (
                            <div key={index} className="assignment-card">
                                <h3>{assignment.assignmentName}</h3>
                                <p>Due Date: {assignment.deadline}</p>
                                <button className = "button">Submit</button>
                            </div>
                        );
                    })}
                </div>
            </div>
        </>
    );
}

export default Assignments;