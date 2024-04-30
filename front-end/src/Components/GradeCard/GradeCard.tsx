import React from 'react';
import './GradeCard.css';

function GradeCard( { assignmentName, grade } : {assignmentName : string, grade : number | string | null}) {
    return (
        <div className="grade-card-container">
            <h3 className="assignment-name">{assignmentName}</h3>
            <p className="grade-container">{grade}</p>
        </div>
    );
};

export default GradeCard;
