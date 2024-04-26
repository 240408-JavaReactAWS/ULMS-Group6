import Assignment from './AssignmentInterface';

interface Grade {
    userId: number;
    gradeId: number;
    grade: number | null;
    assignment: Assignment;
}


export default Grade;