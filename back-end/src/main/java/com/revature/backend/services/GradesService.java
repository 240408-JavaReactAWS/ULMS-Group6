package com.revature.backend.services;

import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Assignments;
import com.revature.backend.models.Grades;
import com.revature.backend.models.Users;
import com.revature.backend.repos.AssignmentsDAO;
import com.revature.backend.repos.GradesDAO;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GradesService {
    private GradesDAO gradesDAO;
    private AssignmentsDAO assignmentsDAO;
    private UsersDAO usersDAO;

    @Autowired
    public GradesService(GradesDAO gradesDAO, AssignmentsDAO assignmentsDAO, UsersDAO usersDAO) {
        this.gradesDAO = gradesDAO;
        this.assignmentsDAO = assignmentsDAO;
        this.usersDAO = usersDAO;
    }

    public List<Grades> getStudentAllGrades(Integer userId) {
        // Implement logic to retrieve grades for a specific student
        return gradesDAO.findByUserId(userId);
    }

    public List<Grades> getAssignmentAllGrades(Integer assignmentId) {
        // Implement logic to retrieve grades for a specific assignment
        return gradesDAO.findByAssignmentId(assignmentId);
    }

    // Logic for Getting grade for specific student/ assignment
    public Grades getAssignmentGrades(Integer assignmentId, Integer userId) throws NoSuchUserFoundException {
        // Implement logic to retrieve grades for a specific assignment
        Optional<Users> studentOptional = usersDAO.findById(userId);
      if (studentOptional.isPresent()) {
          Users student = studentOptional.get();
          //Using method in GradesDAO to find grade by user and assignment
          return gradesDAO.findByAssignmentIdAndUserId(assignmentId, userId);
      } else
          throw new NoSuchUserFoundException("No student found with ID: " + userId);
    }

    @Transactional
    public Grades assignGrade(Integer assignmentId, Integer userId, Double grade) {
        // Implement logic to assign/update grade for a student's assignment
        // You may need to perform additional validation
        Grades existingGrade = gradesDAO.findByAssignmentIdAndUserId(assignmentId, userId);
        if (existingGrade != null) {
            existingGrade.setGrade(grade);
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

}
