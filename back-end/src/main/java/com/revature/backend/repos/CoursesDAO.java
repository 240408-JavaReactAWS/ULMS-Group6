package com.revature.backend.repos;

import com.revature.backend.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesDAO extends JpaRepository<Courses, Integer> {
}
