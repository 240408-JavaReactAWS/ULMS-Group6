package com.revature.backend.repos;

import com.revature.backend.models.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the Assignments Data Access Object (DAO) in the application.
 * It extends JpaRepository to inherit the default methods for CRUD operations.
 * It also includes custom methods for finding assignments by course and student.
 */
@Repository
public interface AssignmentsDAO extends JpaRepository<Assignments, Integer> {

    /**
     * Finds assignments for a specific course that a specific student is enrolled in.
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     * @return A list of assignments for the specified course that the specified student is enrolled in.
     */
    List<Assignments> findByCourse_Students_Student_UserIdAndCourse_CourseId(Integer studentId, Integer courseId);

    /**
     * Finds assignments for a specific course.
     * @param courseId The ID of the course.
     * @return A list of assignments for the specified course.
     */
    List<Assignments> findByCourse_CourseId(Integer courseId);
}