package com.revature.backend.Repo;

import com.revature.backend.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesDAO extends JpaRepository<Courses, Integer> {
}
