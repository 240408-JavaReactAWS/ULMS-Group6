import { ChangeEvent, FormEvent, useEffect, useState } from 'react'
import axios from 'axios';
import { Assignment } from './Assignments';
import './Assignments.css';

export default function AssignmentTeacher() {


    const [name, setName] = useState('')
    const [deadline, setDeadline] = useState('');

    const [assignments, setAssignments] = useState<Assignment[]>([]);


    useEffect(() => {
        axios.get('http://localhost:8080/users/2/courses/1/assignments').then(response => {
            // console.log(response.data);
            setAssignments(response.data);
        }).catch(error => {
            console.log('Errors retriving Assignments', error);
        });
    }, []);


    let addName = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }

    let addDeadline = (e: React.ChangeEvent<HTMLInputElement>) => {
        setDeadline(e.target.value);
    }

    let submitNewAssignment = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const formattedDeadline = formatDeadline(deadline);
            let res = await axios.post('http://localhost:8080/courses/1/assignments', {
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

    let deleteAssignment = async (assignmentId: string) => {
        try {
            let res = await axios.delete(`http://localhost:8080/courses/1/assignments/${assignmentId}`, { withCredentials: false });
            if (res.status === 200) {
                console.log("Assignment Deleted");
            }
            setAssignments(assignments.filter(assignment => assignment.assignmentsId !== assignmentId));
        } catch (error) {
            console.log("Error deleting plan");
            alert("Error deleting plan");
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
                        <input type='text' name='name' onChange={addName} /><br />
                    </label>
                    <label>
                        Assignment Due Date:
                        <input type='Date' name='Deadline' onChange={addDeadline} /><br />
                    </label>
                    <button type="submit" className="btn">
                        Add
                    </button>
                </form>
            </div>
            <div>
                {
                    assignments.map((assignment: Assignment, index: number) => (
                        <div key={index} className="assignment-block">
                            <h2>Assignment: {assignment.assignmentName}</h2>
                            <p>Due Date: {assignment.deadline}</p>
                            <button onClick={() => {
                                const id = assignment.assignmentsId
                                deleteAssignment(id);
                            }}>Delete Assignment</button>
                        </div>
                    ))
                }
            </div>
        </>
    )
}
