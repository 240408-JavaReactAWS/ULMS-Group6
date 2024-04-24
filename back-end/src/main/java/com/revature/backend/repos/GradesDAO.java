package com.revature.backend.repos;

import com.revature.backend.models.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradesDAO extends JpaRepository<Grades,Integer> {
    List<Grades> findByAssignmentAssignmentsId(Integer assignmentId);
    List<Grades> findByUserUserId(Integer userId);
    Grades findByAssignmentAssignmentsIdAndUserUserId(Integer assignmentId, Integer userId);
}
