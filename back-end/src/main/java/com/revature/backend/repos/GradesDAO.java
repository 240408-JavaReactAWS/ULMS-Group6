package com.revature.backend.repos;

import com.revature.backend.models.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradesDAO extends JpaRepository<Grades,Integer> {
    List<Grades> findByUserId(Integer userId);
    List<Grades> findByAssignmentId(Integer assignmentId);
    Grades findByAssignmentIdAndUserId(Integer assignmentId,Integer userId);
}
