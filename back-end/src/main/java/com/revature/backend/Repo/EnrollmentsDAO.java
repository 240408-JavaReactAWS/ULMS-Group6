
package com.revature.backend.Repo;


import com.revature.backend.Model.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentsDAO extends JpaRepository<Enrollments, Integer> {
}
