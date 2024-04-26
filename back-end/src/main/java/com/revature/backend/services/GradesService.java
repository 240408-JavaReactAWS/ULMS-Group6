package com.revature.backend.services;

import com.revature.backend.config.UserDTO;
import com.revature.backend.config.UserGradesDTO;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Assignments;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Grades;
import com.revature.backend.models.Users;
import com.revature.backend.repos.AssignmentsDAO;
import com.revature.backend.repos.CoursesDAO;
import com.revature.backend.repos.GradesDAO;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GradesService {
    private GradesDAO gradesDAO;
    private AssignmentsDAO assignmentsDAO;
    private UsersDAO usersDAO;
    private CoursesDAO coursesDAO;

    @Autowired
    public GradesService(GradesDAO gradesDAO, AssignmentsDAO assignmentsDAO, UsersDAO usersDAO, CoursesDAO coursesDAO) {
        this.gradesDAO = gradesDAO;
        this.assignmentsDAO = assignmentsDAO;
        this.usersDAO = usersDAO;
        this.coursesDAO = coursesDAO;
    }

    //logic to retrieve grades for a specific student
    public List<Grades> getStudentAllGrades(Integer userId) {
        // Implement logic to retrieve grades for a specific student
        return gradesDAO.findByUserUserId(userId);
    }

    public List<Grades> getAssignmentAllGrades(Integer assignmentId) {
        // Implement logic to retrieve grades for a specific assignment
        return gradesDAO.findByAssignmentAssignmentsId(assignmentId);
    }

    // Logic for Getting grade for specific student/ assignment
    public Grades getAssignmentGrades(Integer assignmentId, Integer userId) throws NoSuchUserFoundException {
        // Implement logic to retrieve grades for a specific assignment
        Optional<Users> studentOptional = usersDAO.findById(userId);
      if (studentOptional.isPresent()) {
          Users student = studentOptional.get();
          //Using method in GradesDAO to find grade by user and assignment
          return gradesDAO.findByAssignmentAssignmentsIdAndUserUserId(assignmentId, userId);
      } else
          throw new NoSuchUserFoundException("No student found with ID: " + userId);
    }



    @Transactional
    public Grades assignGrade(Integer assignmentId, Integer userId, Double grade) {
        // Implement logic to assign/update grade for a student's assignment
        // You may need to perform additional validation
        Grades existingGrade = gradesDAO.findByAssignmentAssignmentsIdAndUserUserId(assignmentId, userId);
        if (existingGrade != null) {
            System.out.println("orignal" + existingGrade.getGrade());
            existingGrade.setGrade(grade);
            System.out.println("Updated" + existingGrade.getGrade());
            return gradesDAO.save(existingGrade);
        } else {
            // Create a new grade
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



    public List<UserGradesDTO> getAllGradesForCourse(Integer courseId) {
        try {
            // Get the course
            Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));

            // Get the students in the course
            Set<Users> students = course.getStudents();

            // Create a list to hold the result
            List<UserGradesDTO> studentGradesList = new ArrayList<>();

            // Iterate over each student
            for (Users student : students) {
                // Get all grades for the student
                List<Grades> grades = gradesDAO.findByUserUserId(student.getUserId());

                // Create a UserDTO object with the student's id, first name, and last name
                UserDTO userDTO = new UserDTO();
                userDTO.setId(student.getUserId());
                userDTO.setFirstName(student.getFirstName());
                userDTO.setLastName(student.getLastName());

                // Create a UserGradesDTO object and add it to the list
                UserGradesDTO userGradesDTO = new UserGradesDTO();
                userGradesDTO.setUser(userDTO);
                userGradesDTO.setGrades(grades);
                studentGradesList.add(userGradesDTO);
            }

            return studentGradesList;
        } catch (NoSuchCourseException e) {
            // Log the exception and return an empty list
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

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
}

