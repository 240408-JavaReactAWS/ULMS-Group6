package com.revature.backend.services;

import com.revature.backend.config.UserDTO;
import com.revature.backend.config.UserGradesDTO;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.*;
import com.revature.backend.repos.AssignmentsDAO;
import com.revature.backend.repos.CoursesDAO;
import com.revature.backend.repos.GradesDAO;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service class for handling operations related to Grades.
 */
@Service
public class GradesService {
    private GradesDAO gradesDAO;
    private AssignmentsDAO assignmentsDAO;
    private UsersDAO usersDAO;
    private CoursesDAO coursesDAO;

    /**
     * Constructs a GradesService with the specified GradesDAO, AssignmentsDAO, UsersDAO, and CoursesDAO.
     * @param gradesDAO the DAO to manage grades
     * @param assignmentsDAO the DAO to manage assignments
     * @param usersDAO the DAO to manage users
     * @param coursesDAO the DAO to manage courses
     */
    @Autowired
    public GradesService(GradesDAO gradesDAO, AssignmentsDAO assignmentsDAO, UsersDAO usersDAO, CoursesDAO coursesDAO) {
        this.gradesDAO = gradesDAO;
        this.assignmentsDAO = assignmentsDAO;
        this.usersDAO = usersDAO;
        this.coursesDAO = coursesDAO;
    }

    /**
     * Retrieves all grades for a specific student.
     * @param userId the ID of the student
     * @return a list of grades for the specified student
     */
    public List<Grades> getStudentAllGrades(Integer userId) {
        return gradesDAO.findByUserUserId(userId);
    }

    /**
     * Retrieves all grades for a specific assignment.
     * @param assignmentId the ID of the assignment
     * @return a list of grades for the specified assignment
     */
    public List<Grades> getAssignmentAllGrades(Integer assignmentId) {
        return gradesDAO.findByAssignmentAssignmentsId(assignmentId);
    }

    /**
     * Retrieves a grade for a specific student and assignment.
     * @param assignmentId the ID of the assignment
     * @param userId the ID of the student
     * @return the grade for the specified student and assignment
     * @throws NoSuchUserFoundException if no student is found with the specified ID
     */
    public Grades getAssignmentGrades(Integer assignmentId, Integer userId) throws NoSuchUserFoundException {
        Optional<Users> studentOptional = usersDAO.findById(userId);
        if (studentOptional.isPresent()) {
            return gradesDAO.findByAssignmentAssignmentsIdAndUserUserId(assignmentId, userId);
        } else {
            throw new NoSuchUserFoundException("No student found with ID: " + userId);
        }
    }

    /**
     * Assigns or updates a grade for a student's assignment.
     * @param assignmentId the ID of the assignment
     * @param userId the ID of the student
     * @param grade the grade to be assigned
     * @return the assigned or updated grade
     */
    @Transactional
    public Grades assignGrade(Integer assignmentId, Integer userId, Double grade) {
        Grades existingGrade = gradesDAO.findByAssignmentAssignmentsIdAndUserUserId(assignmentId, userId);
        if (existingGrade != null) {
            existingGrade.setGrade(grade);
            return gradesDAO.save(existingGrade);
        } else {
            Assignments assignment = assignmentsDAO.findById(assignmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

            Users user = usersDAO.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            Grades newGrade = new Grades();
            newGrade.setAssignment(assignment);
            newGrade.setUser(user);
            newGrade.setGrade(grade);
            return gradesDAO.save(newGrade);
        }
    }

    /**
     * Retrieves all grades for a specific course.
     * @param courseId the ID of the course
     * @return a list of UserGradesDTO objects, each containing a student and their grades
     */
    public List<UserGradesDTO> getAllGradesForCourse(Integer courseId) {
        try {
            Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));
            Set<CourseStudent> students = course.getStudents();
            List<UserGradesDTO> studentGradesList = new ArrayList<>();
            for (CourseStudent student : students) {
                Users user = student.getStudent();
                List<Grades> grades = gradesDAO.findByUserUserId(user.getUserId());
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getUserId());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                UserGradesDTO userGradesDTO = new UserGradesDTO();
                userGradesDTO.setUser(userDTO);
                userGradesDTO.setGrades(grades);
                studentGradesList.add(userGradesDTO);
            }
            return studentGradesList;
        } catch (NoSuchCourseException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Assigns grades to multiple students for multiple assignments.
     * @param userGradesList a list of UserGradesDTO objects, each containing a student and their grades
     * @return a list of the assigned grades
     */
    public List<Grades> assignBulkGrades(List<UserGradesDTO> userGradesList) {
        List<Grades> assignedGrades = new ArrayList<>();
        for (UserGradesDTO userGrades : userGradesList) {
            UserDTO userDTO = userGrades.getUser();
            for (Grades grade : userGrades.getGrades()) {
                Grades assignedGrade = assignGrade(grade.getAssignment().getAssignmentsId(), userDTO.getId(), grade.getGrade());
                assignedGrades.add(assignedGrade);
            }
        }
        return assignedGrades;
    }

    /**
     * Retrieves all grades for a specific user in a course.
     * @param courseId the ID of the course
     * @param userId the ID of the user
     * @return a list of grades for the specified user in the course
     */
    public List<Grades> getGradesForUserInCourse(Integer courseId, Integer userId) {
        List<Assignments> assignments = assignmentsDAO.findByCourse_CourseId(courseId);
        List<Grades> userGrades = new ArrayList<>();
        for (Assignments assignment : assignments) {
            Grades grade = gradesDAO.findByAssignmentAssignmentsIdAndUserUserId(assignment.getAssignmentsId(), userId);
            if (grade != null) {
                userGrades.add(grade);
            }
        }
        return userGrades;
    }
}