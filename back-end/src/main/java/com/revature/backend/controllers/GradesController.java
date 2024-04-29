package com.revature.backend.controllers;

import com.revature.backend.config.UserDTO;
import com.revature.backend.config.UserGradesDTO;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Grades;
import com.revature.backend.models.Users;
import com.revature.backend.services.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller class for handling HTTP requests related to Grades.
 */
@RestController
@RequestMapping("{courseId}/grades")
@CrossOrigin(origins = {"http://localhost:3000"})
public class GradesController {
    private final GradesService gradesService;

    /**
     * Constructs a GradesController with the specified GradesService.
     * @param gradesService the service to manage grades
     */
    @Autowired
    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    /**
     * Handles the GET request to retrieve all grades for a student.
     * @param userId the ID of the student
     * @return a ResponseEntity containing a list of all grades for the student
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Grades>> getStudentGrades(@PathVariable("userId") Integer userId) {
        List<Grades> studentGrades = gradesService.getStudentAllGrades(userId);
        return ResponseEntity.ok(studentGrades);
    }

    /**
     * Handles the GET request to retrieve all grades for an assignment.
     * @param assignmentId the ID of the assignment
     * @return a ResponseEntity containing a list of all grades for the assignment
     */
    @GetMapping()
    public ResponseEntity<List<Grades>> getAssignmentGrades(@PathVariable("assignmentId") Integer assignmentId) {
        List<Grades> assignmentGrades = gradesService.getAssignmentAllGrades(assignmentId);
        return ResponseEntity.ok(assignmentGrades);
    }

    /**
     * Handles the GET request to retrieve all grades for a course.
     * @param courseId the ID of the course
     * @return a ResponseEntity containing a list of UserGradesDTO for the course
     * @throws NoSuchCourseException if the course does not exist
     */
    @GetMapping("course")
    public ResponseEntity<List<UserGradesDTO>> getAllGradesForCourse(@PathVariable("courseId") Integer courseId) throws NoSuchCourseException {
        List<UserGradesDTO> courseGrades = gradesService.getAllGradesForCourse(courseId);
        return ResponseEntity.ok(courseGrades);
    }

    /**
     * Handles the GET request to retrieve a grade for a specific student and assignment.
     * @param assignmentId the ID of the assignment
     * @param userId the ID of the student
     * @return a ResponseEntity containing the grade
     * @throws NoSuchUserFoundException if the user does not exist
     */
    @GetMapping("/{assignmentId}/{userId}")
    public ResponseEntity<Grades> getAssignmentGrades(@PathVariable("assignmentId") Integer assignmentId ,
                                                      @PathVariable("userId") Integer userId)
            throws NoSuchUserFoundException {
        Grades assignmentGrades = gradesService.getAssignmentGrades(assignmentId, userId);
        return ResponseEntity.ok(assignmentGrades);
    }

    /**
     * Handles the POST request to assign a grade to a specific student and assignment.
     * @param assignmentId the ID of the assignment
     * @param userId the ID of the student
     * @param grade the grade to be assigned
     * @return a ResponseEntity containing the assigned grade, or a status of INTERNAL_SERVER_ERROR if the grade could not be assigned
     */
    @PostMapping("/{assignmentId}/{userId}")
    public ResponseEntity<Grades> assignGrade(@PathVariable("assignmentId") Integer assignmentId,
                                              @PathVariable("userId") Integer userId,
                                              @RequestParam Double grade) {
        Grades assignedGrade = gradesService.assignGrade(assignmentId, userId, grade);
        if (assignedGrade != null) {
            return ResponseEntity.ok(assignedGrade);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Handles the POST request to assign grades in bulk.
     * @param userGradesList a list of UserGradesDTO containing the grades to be assigned
     * @return a ResponseEntity containing a list of the assigned grades, or a status of INTERNAL_SERVER_ERROR if the grades could not be assigned
     */
    @PostMapping("/save-grades")
    public ResponseEntity<List<Grades>> assignBulkGrades(@RequestBody List<UserGradesDTO> userGradesList) {
        List<Grades> assignedGrades = gradesService.assignBulkGrades(userGradesList);
        if (assignedGrades != null) {
            return ResponseEntity.ok(assignedGrades);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}