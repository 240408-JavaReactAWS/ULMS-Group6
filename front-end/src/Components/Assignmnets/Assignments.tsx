import { useEffect, useState } from 'react'
import axios from 'axios';
import { error } from 'console';
import './Assignments.css';
import Assignment from '../../interfaces/AssignmentInterface';

interface AssignmentsProps {
    userId: number,
    courseId: number
}

function Assignments({ userId, courseId }: AssignmentsProps) {
    const [assignments, setAssignments] = useState<Assignment[]>([]);
    //For rendering when teacher add/ removes assignments
    const [fetching, setFetching] = useState(false);

    useEffect(() => {
        const fetchAssignments = async () => {
            setFetching(true);
            try {
                const response = await axios.get(`http://localhost:8080/users/${userId}/courses/${courseId}/assignments`);
                setAssignments(response.data);
            } catch (error) {
                console.log('Errors retrieving Assignments', error);
            } finally {
                setFetching(false);
            }
        };
        //For rendering when teacher add/ removes assignments
        fetchAssignments();
    }, [userId, courseId, fetching]);

    return (
        <>
            <div>
                <h1 className="title">Assignments</h1>
                <div className="assignment-container">
                    {assignments.map((assignment, index) => {
                        return (
                            <div key={index} className="assignment-card">
                                <h3>Assignment Name: {assignment.assignmentName}
                                    <br />Due Date: {assignment.deadline}
                                </h3>
                                <button className="button">Submit</button>
                            </div>
                        );
                    })}
                </div>
            </div>
        </>
    );
}

export default Assignments;