package com.revature.backend.services;


import com.revature.backend.models.Assignments;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Grades;
import com.revature.backend.models.Users;
import com.revature.backend.repos.AssignmentsDAO;
import com.revature.backend.repos.CoursesDAO;
import com.revature.backend.repos.GradesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class AssignmentsService {
    private AssignmentsDAO assignmentsDAO;
    private CoursesDAO coursesDAO;
    private GradesDAO gradesDAO;

    @Autowired
    public AssignmentsService(AssignmentsDAO assignmentsDAO, CoursesDAO coursesDAO, GradesDAO gradesDAO) {
        this.assignmentsDAO = assignmentsDAO;
        this.coursesDAO = coursesDAO;
        this.gradesDAO = gradesDAO;
    }

    @Transactional
    public Assignments createAssignment(Integer courseId, Assignments assignment) {
        // Retrieve the course using the courseId
        Courses course = coursesDAO.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // Associate the assignment with the course
        assignment.setCourse(course);

        // Save the assignment to the database
        Assignments newAssignment = assignmentsDAO.save(assignment);

        // Create default grades for each student enrolled in the course
        Set<Users> students = course.getStudents();
        for (Users student : students) {
            Grades defaultGrade = new Grades();
            defaultGrade.setAssignment(newAssignment);
            defaultGrade.setUser(student);
            defaultGrade.setGrade(null); // Set default grade to null initially
            gradesDAO.save(defaultGrade);
        }

        return newAssignment;
    }

    public void deleteAssignment(Integer assignmentId) {
        List<Grades> gradesToDelete = gradesDAO.findByAssignmentAssignmentsId(assignmentId);
        // Delete each grade
        for (Grades grade : gradesToDelete) {
            gradesDAO.delete(grade);
        }
        assignmentsDAO.deleteById(assignmentId);
    }

    public List<Assignments> getAllAssignmentsByCourseId(Integer courseId) {
        return assignmentsDAO.findByCourse_CourseId(courseId);
    }
}
