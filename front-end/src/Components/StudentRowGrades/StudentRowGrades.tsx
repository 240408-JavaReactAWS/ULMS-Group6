import React from 'react';

interface Assignment {
    assignmentsId: number;
    assignmentName: string;
  }
  
  interface User {
    id: number;
    firstName: string;
    lastName: string;
  }
  
  interface Grade {
    gradeId: number;
    grade: number | null;
    assignment: Assignment;
  }
  
  interface Student {
    user: {
      id: number;
      firstName?: string;
      lastName?: string;
    };
    grades: Grade[];
  }
  
  interface StudentRowGradesProps {
    student: Student;
    assignments: Assignment[];
    handleGradeChange: (userId: number, assignmentId: number, newGrade: number) => void;
  }
  

/**
 * Renders a row for a student's grades.
 * @param {Object} props - The component props.
 * @param {Object} props.student - The student object.
 * @param {Array} props.assignments - The list of assignments.
 * @param {Function} props.handleGradeChange - The function to handle grade changes.
 * @returns {JSX.Element} - The student row grades component.
 */
const StudentRowGrades: React.FC<StudentRowGradesProps> = ({ student, assignments, handleGradeChange }) => {
    return (
        <tr key={student.user.id}>
            <td>{`${student.user.firstName} ${student.user.lastName}`}</td>
            {assignments.map(assignment => {
                const grade = student.grades.find(g => g.assignment.assignmentsId === assignment.assignmentsId);
                return (
                    <td key={assignment.assignmentsId}>
                        <input
                            type="number"
                            value={grade ? grade.grade || '' : ''}
                            onChange={e => handleGradeChange(student.user.id, assignment.assignmentsId, parseFloat(e.target.value))}
                        />
                    </td>
                );
            })}
        </tr>
    );
};

export default StudentRowGrades;