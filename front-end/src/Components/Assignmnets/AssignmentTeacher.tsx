import { ChangeEvent, FormEvent, useEffect, useState } from 'react'
import axios from 'axios';
import './Assignments.css';
import Assignment from '../../interfaces/AssignmentInterface';
import { useParams } from 'react-router-dom';
import './AssignmentTeacher.css';

export default function AssignmentTeacher() {


    const [name, setName] = useState('')
    const [deadline, setDeadline] = useState('');

    const [assignments, setAssignments] = useState<Assignment[]>([]);

    const courseId = useParams<{ courseId: string }>().courseId;

    useEffect(() => {
        axios.get(`http://localhost:8080/courses/${courseId}/assignments`).then(response => {
            // console.log(response.data);

            if (Array.isArray(response.data)) {
                setAssignments(response.data);
            } else {
                console.error('Error retrieving Assignments', response.data);
                setAssignments([]);
            }
        }).catch(error => {
            console.log('Errors retriving Assignments', error);
        });
    }, [courseId]);


    let addName = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }

    let addDeadline = (e: React.ChangeEvent<HTMLInputElement>) => {
        setDeadline(e.target.value);
    }


    let submitNewAssignment = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            const formattedDeadline = formatDeadline(deadline);
            let res = await axios.post(`http://localhost:8080/courses/${courseId}/assignments`, {
                assignmentName: name,
                deadline: formattedDeadline
            });
            if (res.status === 201) {
                // Update the assignments state with the new assignment
                const newAssignment = res.data; // Assuming the API response returns the new assignment
                setAssignments([newAssignment, ...assignments]);
                // Clear the form fields if needed
                setName('');
                setDeadline('');
            }
        } catch (error) {
            console.log("Error creating Assignment");
        }
    }

    let deleteAssignment = async (assignmentId: number) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this assignment?");
        if (confirmDelete) {
            try {
                let res = await axios.delete(`http://localhost:8080/courses/${courseId}/assignments/${assignmentId}`, { withCredentials: false });
                if (res.status === 200) {
                    console.log("Assignment Deleted");
                }
                setAssignments(assignments.filter(assignment => assignment.assignmentsId !== assignmentId));
            } catch (error) {
                console.log("Error deleting plan");
                alert("Error deleting plan");
            }
        }
    }


    const formatDeadline = (deadline: string) => {
        // Convert the input date (YYYY-MM-DD) to the required format (YYYY/MM/DD)
        const parts = deadline.split('-');
        if (parts.length === 3) {
            const [year, month, day] = parts;
            return `${year}/${month}/${day}`;
        }
        return deadline; // Return the original value if unable to parse
    };

    return (
        <>
            <div className='new-assignment-form'>
                <form onSubmit={submitNewAssignment} className='new-Assignment'>
                    <label>
                        Assignment Name:
                        <input type='text' name='name' onChange={addName} required value={name}/><br />
                    </label>
                    <label>
                        Assignment Due Date:
                        <input type='Date' name='Deadline' onChange={addDeadline} required value={deadline}/><br />
                    </label>
                    <button type="submit" className="btn" >
                        Add
                    </button>
                </form>
            </div>
            <div>
                {
                    assignments?.map((assignment: Assignment, index: number) => (
                        <div key={index} className="assignment-card">
                            <div className="assignment">
                                <h3>Assignment Name: {assignment.assignmentName}</h3>
                                <p>Dedline: {assignment.deadline}</p>
                            </div>


                            <button className="delete-button" onClick={() => {
                                deleteAssignment(assignment.assignmentsId);
                            }}>Delete</button>
                        </div>
                    ))
                }
            </div>
        </>
    )
}
