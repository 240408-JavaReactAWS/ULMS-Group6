package com.revature.backend.repos;

import com.revature.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
<<<<<<< Updated upstream
=======

    //find user by username and password
    boolean findByUsernameAndPassword(String username, String password);

//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Users u WHERE u.username = :username AND u.password = :password")
//    boolean existsByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    //Used in- As a Student, I can check my assignments and due dates.
    Users findByUserId(Integer userId);
>>>>>>> Stashed changes
}
