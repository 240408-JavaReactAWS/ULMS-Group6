package com.revature.backend.Repo;

import com.revature.backend.Model.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentsDAO extends JpaRepository<Assignments, Integer> {
}
