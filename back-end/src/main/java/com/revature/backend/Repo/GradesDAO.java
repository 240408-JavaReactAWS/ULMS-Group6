package com.revature.backend.Repo;

import com.revature.backend.Model.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesDAO extends JpaRepository<Grades, Integer> {
}
