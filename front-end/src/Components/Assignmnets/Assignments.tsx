import { useEffect, useState } from 'react'
import axios from 'axios';
import { error } from 'console';

interface Assignment {
    assignmentName: string;
    deadline: any;
}

function Assignments() {
    const [assignments, setAssignments] = useState<Assignment[]>([]);

    useEffect(() => {
        axios.get('http://localhost:8080/users/2/courses/1/assignments').then(response => {
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
                            <h3>{assignment.assignmentName}</h3>
                            <p>Due Date: {assignment.deadline}</p>
                        </div>
                    ))
                }
            </div>
        </>
    );
}

export default Assignments;