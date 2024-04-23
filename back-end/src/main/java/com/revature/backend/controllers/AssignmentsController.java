package com.revature.backend.controllers;

import com.revature.backend.models.Assignments;
import com.revature.backend.services.AssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/{courseId}/assignments")
public class AssignmentsController {
    private final AssignmentsService assignmentsService;

    @Autowired
    public AssignmentsController(AssignmentsService assignmentsService) {
        this.assignmentsService = assignmentsService;
    }

    @PostMapping
    public ResponseEntity<Assignments> createAssignment(@PathVariable("courseId") Integer courseId, @RequestBody Assignments assignment) {
        Assignments createdAssignment = assignmentsService.createAssignment(courseId, assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAssignment);
    }

    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable("assignmentId") Integer assignmentId) {
        assignmentsService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();
    }
}
