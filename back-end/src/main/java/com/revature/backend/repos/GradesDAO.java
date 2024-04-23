package com.revature.backend.repos;

import com.revature.backend.models.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesDAO extends JpaRepository<Grades,Integer> {
}
