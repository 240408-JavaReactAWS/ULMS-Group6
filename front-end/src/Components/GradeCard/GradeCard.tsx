import React from 'react';

function GradeCard( { assignmentName, grade } : {assignmentName : string, grade : number | string | null}) {
    return (
        <div>
            <h3>{assignmentName}</h3>
            <p>Grade: {grade}</p>
        </div>
    );
};

export default GradeCard;
