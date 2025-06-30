package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    // Vulnerable: prone to SQL Injection
    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    User login(String username, String password);
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

}

