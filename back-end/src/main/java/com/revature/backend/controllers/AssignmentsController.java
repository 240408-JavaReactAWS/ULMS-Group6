package com.revature.backend.controllers;

import com.revature.backend.models.Assignments;
import com.revature.backend.services.AssignmentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to Assignments.
 */
@RestController
@RequestMapping("/courses/{courseId}/assignments")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AssignmentsController {
    private final AssignmentsService assignmentsService;

    /**
     * Constructs an AssignmentsController with the specified AssignmentsService.
     * @param assignmentsService the service to manage assignments
     */
    public AssignmentsController(AssignmentsService assignmentsService) {
        this.assignmentsService = assignmentsService;
    }

    /**
     * Handles the GET request to retrieve all assignments for a course.
     * @param courseId the ID of the course
     * @return a ResponseEntity containing a list of all assignments for the course, or a no content status if the list is empty
     */
    @GetMapping
    public ResponseEntity<List<Assignments>> getAssignments(@PathVariable Integer courseId) {
        List<Assignments> assignments = assignmentsService.getAllAssignmentsByCourseId(courseId);
        if(assignments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    /**
     * Handles the POST request to create an assignment for a course.
     * @param courseId the ID of the course
     * @param assignment the assignment to be created
     * @return a ResponseEntity containing the created assignment, with a created status
     */
    @PostMapping
    public ResponseEntity<Assignments> createAssignment(@PathVariable Integer courseId, @RequestBody Assignments assignment) {
        Assignments createdAssignment = assignmentsService.createAssignment(courseId, assignment);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }

    /**
     * Handles the DELETE request to delete an assignment by its ID.
     * @param assignmentId the ID of the assignment to be deleted
     * @return a ResponseEntity with a no content status
     */
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Integer assignmentId) {
        assignmentsService.deleteAssignment(assignmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}