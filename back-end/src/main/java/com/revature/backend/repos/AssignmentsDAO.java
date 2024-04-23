package com.revature.backend.repos;

import com.revature.backend.models.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentsDAO extends JpaRepository<Assignments, Integer> {
}
