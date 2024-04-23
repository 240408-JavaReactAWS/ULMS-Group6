package com.revature.backend.repos;

import com.revature.backend.models.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentsDAO extends JpaRepository<Assignments, Integer> {
    //As a Student, I can check my assignments and due dates.
    List<Assignments> findByCourseId(Integer courseId);
}
