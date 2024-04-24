package com.revature.backend.repos;

import com.revature.backend.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesDAO extends JpaRepository<Courses, Integer> {
    Optional<Courses> findCourseByCourseName(String name);
}
