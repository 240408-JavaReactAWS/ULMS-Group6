package com.revature.backend.Repo;

import com.revature.backend.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO  extends JpaRepository<Users, Integer> {
}
