package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

    // Custom query method to find a user by username or email
    //User findByUsernameOrEmail(String username, String email);

    // Custom query method to find a user by username and password (for authentication)
    //User findByUsernameAndPassword(String username, String password);

    // Custom query method to delete a user by username
    /*
    void deleteByUsername(String username);

    // Custom query method to delete a user by email
    void deleteByEmail(String email);

    */
}