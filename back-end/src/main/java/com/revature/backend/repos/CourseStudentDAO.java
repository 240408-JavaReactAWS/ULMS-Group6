package com.revature.backend.repos;

import com.revature.backend.models.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentDAO extends JpaRepository<CourseStudent, Integer> {

    CourseStudent findByStudent_UserId(int id);

}
