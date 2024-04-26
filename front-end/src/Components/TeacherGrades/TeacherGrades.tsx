import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AssignmentColumnGrades from '../AssignmentColumGrades/AssignmentColumGrades';
import StudentRowGrades from '../StudentRowGrades/StudentRowGrades';

interface Assignment {
    assignmentsId: number;
    assignmentName: string;
    deadline: string;
  }
  
  interface Student {
    user: {
      id: number;
      firstName?: string;
      lastName?: string;
    };
    grades: Grade[];
  }

interface Grade {
    user: {
    id: number;
    firstName?: string;
    lastName?: string;
    };
    grades: {
    gradeId: number;
    grade: number | null;
    assignment: Assignment;
    }[];
}
const TeacerGrades = () => {
  const [assignments, setAssignments] = useState<Assignment[]>([]);
  const [grades, setGrades] = useState<Grade[]>([]);

  useEffect(() => {
    fetchAssignments();
    fetchGrades();
  }, []);

  const fetchAssignments = () => {
    axios.get('http://localhost:8080/courses/1/assignments')
      .then(response => {
        setAssignments(response.data);
      })
      .catch(error => {
        console.error('Error fetching assignments:', error);
      });
  };

  const fetchGrades = () => {
    axios.get('http://localhost:8080/1/grades/course')
      .then(response => {
        setGrades(response.data);
      })
      .catch(error => {
        console.error('Error fetching grades:', error);
      });
  };

const handleGradeChange = (userId: number, assignmentId: number, newGrade: number) => {
    const updatedGrades = grades.map(student => {
      if (student.user.id === userId) {
        return {
          ...student,
          grades: student.grades.map(grade => {
            if (grade.assignment.assignmentsId === assignmentId) {
              return {
                ...grade,
                grade: newGrade
              };
            }
            return grade;
          })
        };
      }
      return student;
    });
    setGrades(updatedGrades);
  };

const handleSaveChanges = () => {
    axios.post('http://localhost:8080/1/grades/save-grades', grades)
        .then(response => {
            console.log('Changes saved successfully:', response.data);
        })
        .catch(error => {
            console.error('Error saving changes:', error);
        });
};

  return (
    <div>
      <h1>Teacher Page</h1>
      
    <table>
        <thead>
            <tr>
                <th>Student</th>
                {assignments.map((assignment) => (
                    <AssignmentColumnGrades key={assignment.assignmentsId} assignment={assignment} />
                ))}
            </tr>
        </thead>
        <tbody>
            {grades.map((student) => (
                <StudentRowGrades
                    key={student.user.id}
                    student={student}
                    assignments={assignments}
                    handleGradeChange={handleGradeChange}
                />
            ))}
        </tbody>
    </table>
    <button onClick={handleSaveChanges}>Save Changes</button>
    </div>
  );
};

export default TeacerGrades;