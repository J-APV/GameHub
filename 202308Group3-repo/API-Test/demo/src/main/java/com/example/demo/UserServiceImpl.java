package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails createUser(UserDetails user) {
        // You can add additional business logic or validation here before saving the user
        return userRepository.save(user);
    }

    // Add more methods for user-related operations as needed
}
