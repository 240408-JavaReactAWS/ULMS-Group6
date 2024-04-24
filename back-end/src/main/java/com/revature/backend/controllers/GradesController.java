package com.revature.backend.controllers;

import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Grades;
import com.revature.backend.services.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{courseId}/grades")
public class GradesController {
    private final GradesService gradesService;

    @Autowired
    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Grades>> getStudentGrades(@PathVariable("userId") Integer userId) {
        List<Grades> studentGrades = gradesService.getStudentAllGrades(userId);
        return ResponseEntity.ok(studentGrades);
    }

    @GetMapping()
    public ResponseEntity<List<Grades>> getAssignmentGrades(@PathVariable("assignmentId") Integer assignmentId) {
        List<Grades> assignmentGrades = gradesService.getAssignmentAllGrades(assignmentId);
        return ResponseEntity.ok(assignmentGrades);
    }


    // Getting grade for specific student/ assignment
    @GetMapping("/{assignmentId}/{userId}")
    public ResponseEntity<Grades> getAssignmentGrades(@PathVariable("assignmentId") Integer assignmentId ,
                                                      @PathVariable("userId") Integer userId)
                                                        throws NoSuchUserFoundException {
        Grades assignmentGrades = gradesService.getAssignmentGrades(assignmentId, userId);
        return ResponseEntity.ok(assignmentGrades);
    }

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
}
