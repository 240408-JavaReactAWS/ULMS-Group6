package com.revature.backend.repos;

import com.revature.backend.models.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the Announcements Data Access Object (DAO) in the application.
 * It extends JpaRepository to inherit the default methods for CRUD operations.
 * It also includes custom methods for finding announcements by course and student.
 */
@Repository
public interface AnnouncementsDAO extends JpaRepository<Announcements, Integer> {

    /**
     * Finds announcements for a specific course that a specific student is enrolled in.
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     * @return A list of announcements for the specified course that the specified student is enrolled in.
     */
    List<Announcements> findByCourse_Students_Student_UserIdAndCourse_CourseId(Integer studentId, Integer courseId);

    /**
     * Finds announcements for a specific course.
     * @param courseId The ID of the course.
     * @return A list of announcements for the specified course.
     */
    List<Announcements> findByCourse_CourseId(Integer courseId);
}