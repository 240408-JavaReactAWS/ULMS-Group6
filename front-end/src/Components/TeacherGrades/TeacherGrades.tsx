import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AssignmentColumnGrades from '../AssignmentColumGrades/AssignmentColumGrades';
import StudentRowGrades from '../StudentRowGrades/StudentRowGrades';
import { useParams } from 'react-router-dom';
import './TeacherGrades.css';

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

  const courseID = useParams<{ courseId: string }>().courseId;
  const fetchAssignments = () => {
    axios.get(`http://localhost:8080/courses/${courseID}/assignments`)
      .then(response => {
        if (response.data.length === 0) {
          console.log('No assignments found');
          return;
        }
        setAssignments(response.data);
      })
      .catch(error => {
        console.error('Error fetching assignments:', error);
      });
  };

  const fetchGrades = () => {
    axios.get(`http://localhost:8080/${courseID}/grades/course`)
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
    const confirmed = window.confirm('Are you sure you want to save these changes?');
    if (confirmed) {
      axios.post(`http://localhost:8080/${courseID}/grades/save-grades`, grades)
          .then(response => {
              console.log('Changes saved successfully:', response.data);
          })
          .catch(error => {
              console.error('Error saving changes:', error);
          });
    }
};

  return (
    <div className="teacher-page">
      <h1 className="title">Grade Page</h1>
      <table className="grades-table">
        <thead>
            <tr>
                <th className="student-column">Student</th>
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
      <button className="save-button" onClick={handleSaveChanges}>Save Changes</button>
    </div>
  );
};

export default TeacerGrades;