package com.revature.backend.repos;

import com.revature.backend.models.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the Grades Data Access Object (DAO) in the application.
 * It extends JpaRepository to inherit the default methods for CRUD operations.
 * It also includes custom methods for finding grades by assignment and user.
 */
@Repository
public interface GradesDAO extends JpaRepository<Grades,Integer> {

    /**
     * Finds grades for a specific assignment.
     * @param assignmentId The ID of the assignment.
     * @return A list of grades for the specified assignment.
     */
    List<Grades> findByAssignmentAssignmentsId(Integer assignmentId);

    /**
     * Finds grades for a specific user.
     * @param userId The ID of the user.
     * @return A list of grades for the specified user.
     */
    List<Grades> findByUserUserId(Integer userId);

    /**
     * Finds a grade for a specific assignment and user.
     * @param assignmentId The ID of the assignment.
     * @param userId The ID of the user.
     * @return The grade for the specified assignment and user.
     */
    Grades findByAssignmentAssignmentsIdAndUserUserId(Integer assignmentId, Integer userId);
}