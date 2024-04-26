import React from "react";
import Assignment from "../../interfaces/AssignmentInterface";

/**
 * Renders a table header cell for an assignment column with the assignment name.
 * @param {Object} props - The component props.
 * @param {Assignment} props.assignment - The assignment object containing the assignment details.
 * @returns {JSX.Element} The table header cell element.
 */
function AssignmentColumnGrades({ assignment }: { assignment: Assignment }) {
    return <th key={assignment.assignmentsId}>{assignment.assignmentName}</th>;
}

export default AssignmentColumnGrades;