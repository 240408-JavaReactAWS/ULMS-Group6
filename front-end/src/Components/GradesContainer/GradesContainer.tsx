import React, { useState, useEffect } from 'react';
import axios from 'axios';
import GradeCard from '../GradeCard/GradeCard';

interface Assignment {
    assignmentsId: number;
    assignmentName: string;
    deadline: string;
}

interface Grade {
    gradeId: number;
    grade: number | null; // Fix for Problem 1
    assignment: Assignment
}

interface GradesProp {
    courseId: number,
    userId: number
}

/**
 * Renders a container component for displaying grades.
 */
function GradesContainer({courseId, userId}:GradesProp) {
    const [grades, setGrades] = useState<Grade[]>([]); // Specify the type for grades

    useEffect(() => {
        // Make API call to fetch grades
        axios.get(`http://localhost:8080/${courseId}/grades/${userId}`)
            .then(response => {
                setGrades(response.data);
            })
            .catch(error => {
                console.error('Error fetching grades:', error);
            });
    }, [courseId, userId]); // Empty array ensures that this effect runs only once

    let count = 0;
    const totalGrade = grades.reduce((total, grade) => {
        if (grade.grade !== null) {
            count += 100;
            return total + grade.grade;
        }
        return total;
    }, 0);
    const totalGradePercentage = (totalGrade / count) * 100;

    return (
        <div>
            <h1>Grades</h1>
            <ul>
                {grades.map((grade: Grade) => (
                    <GradeCard
                        key={grade.gradeId}
                        assignmentName={grade.assignment.assignmentName}
                        grade={grade.grade !== null ? grade.grade : ''}
                    />
                ))}
            </ul>

            <p>Total Grade: {totalGradePercentage.toPrecision(4)}%</p>
        </div>
    );
}


export default GradesContainer;