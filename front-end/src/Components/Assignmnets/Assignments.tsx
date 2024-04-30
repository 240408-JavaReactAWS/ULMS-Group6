import { useEffect, useState } from 'react'
import axios from 'axios';
import { error } from 'console';
import './Assignments.css';
import Assignment from '../../interfaces/AssignmentInterface';
import { useParams } from 'react-router-dom';

interface AssignmentsProps {
    userId: number,
    courseId: number
}

function Assignments() {
    const courseId = useParams<{ courseId: string }>().courseId;
    const userId= localStorage.getItem("userId");
    const [assignments, setAssignments] = useState<Assignment[]>([]);
    //For rendering when teacher add/ removes assignments
    const [fetching, setFetching] = useState(false);

    useEffect(() => {
        const fetchAssignments = async () => {
            setFetching(true);
            try {
                const response = await axios.get(`http://localhost:8080/courses/${courseId}/assignments`);
                if(Array.isArray(response.data)){
                    setAssignments(response.data);
                }else{
                    console.error('Error retrieving Assignments', response.data);
                    setAssignments([]);
                }
            } catch (error) {
                console.log('Errors retrieving Assignments', error);
            }
        };
        //For rendering when teacher add/ removes assignments
        fetchAssignments();
    }, [userId, courseId]);

    return (
        <>
            <div>
                <h1 className="title">Assignments</h1>
                <div className="assignment-container">
                    {assignments?.map((assignment, index) => {
                        return (
                            <div key={index} className="assignment-card">
                                <div className="assignment">
                                    <h3>Assignment Name: {assignment.assignmentName}</h3>
                                    <p>Dedline:{assignment.deadline}</p> 
                                </div>
                            <button className="delete-button">Submit</button>
                            </div>
                        );
                    })}
                </div>
            </div>
        </>
    );
}

export default Assignments;