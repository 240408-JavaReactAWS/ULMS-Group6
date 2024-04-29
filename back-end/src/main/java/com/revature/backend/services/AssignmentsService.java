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

/**
 * Service class for handling operations related to Assignments.
 */
@Service
public class AssignmentsService {
    private AssignmentsDAO assignmentsDAO;
    private CoursesDAO coursesDAO;
    private GradesDAO gradesDAO;

    /**
     * Constructs an AssignmentsService with the specified AssignmentsDAO, CoursesDAO, and GradesDAO.
     * @param assignmentsDAO the DAO to manage assignments
     * @param coursesDAO the DAO to manage courses
     * @param gradesDAO the DAO to manage grades
     */
    @Autowired
    public AssignmentsService(AssignmentsDAO assignmentsDAO, CoursesDAO coursesDAO, GradesDAO gradesDAO) {
        this.assignmentsDAO = assignmentsDAO;
        this.coursesDAO = coursesDAO;
        this.gradesDAO = gradesDAO;
    }

    /**
     * Creates an assignment for a specific course and creates default grades for each student in the course.
     * @param courseId the ID of the course
     * @param assignment the assignment to be created
     * @return the created assignment
     * @throws IllegalArgumentException if no course is found with the specified ID
     */
    @Transactional
    public Assignments createAssignment(Integer courseId, Assignments assignment) {
        Courses course = coursesDAO.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        assignment.setCourse(course);
        Assignments newAssignment = assignmentsDAO.save(assignment);
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

    /**
     * Deletes an assignment and all associated grades.
     * @param assignmentId the ID of the assignment to be deleted
     */
    public void deleteAssignment(Integer assignmentId) {
        List<Grades> gradesToDelete = gradesDAO.findByAssignmentAssignmentsId(assignmentId);
        for (Grades grade : gradesToDelete) {
            gradesDAO.delete(grade);
        }
        assignmentsDAO.deleteById(assignmentId);
    }

    /**
     * Retrieves all assignments for a specific course.
     * @param courseId the ID of the course
     * @return a list of assignments for the specified course
     */
    public List<Assignments> getAllAssignmentsByCourseId(Integer courseId) {
        return assignmentsDAO.findByCourse_CourseId(courseId);
    }
}