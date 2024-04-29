package com.revature.backend.repos;

import com.revature.backend.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface represents the Courses Data Access Object (DAO) in the application.
 * It extends JpaRepository to inherit the default methods for CRUD operations.
 * It also includes a custom method for finding a course by its name.
 */
@Repository
public interface CoursesDAO extends JpaRepository<Courses, Integer> {

    /**
     * Finds a course by its name.
     * @param name The name of the course.
     * @return An Optional that may contain the course if one with the specified name exists.
     */
    Optional<Courses> findCourseByCourseName(String name);
}